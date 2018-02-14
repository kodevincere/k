package co.kodevincere.k.base.networking.postoffices

import android.util.Log
import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.packages.StringResponse
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.rx.rx_responseString
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by mE on 2/1/18.
 */
class FuelPostOffice: PostOffice() {

    override fun internalSetRootPath(rootPath: String) {
        FuelManager.instance.basePath = this.rootPath
    }

    override fun internalAddPermanentHeaders(key: String, value: String) {
        FuelManager.instance.baseHeaders = this.headers
    }

    override fun internalRemovePermanentHeaders(key: String, value: String) {
        FuelManager.instance.baseHeaders = this.headers
    }

    override fun rxInternalSendPackage(packageToSend: Package, jsonBody: Boolean): Observable<StringResponse> {
        val finalAddress = packageToSend.finalAddress

        val request = when(packageToSend.method){

            HttpMethod.GET -> finalAddress.httpGet(formattedQueries(packageToSend))
            HttpMethod.PUT -> if (jsonBody) finalAddress.httpPut() else finalAddress.httpPut(formDataBody(packageToSend))
            HttpMethod.POST -> if (jsonBody) finalAddress.httpPost() else finalAddress.httpPost(formDataBody(packageToSend))
            HttpMethod.PATCH -> if (jsonBody) finalAddress.httpPatch() else finalAddress.httpPatch(formDataBody(packageToSend))
            HttpMethod.DELETE -> finalAddress.httpDelete(formattedQueries(packageToSend))
        }

        if(jsonBody) {
            request.body(packageToSend.buildJSONBody().toString())
        }

        request.header(packageToSend.headers)

        return request
                .rx_responseString()
                .subscribeOn(Schedulers.io())
                .map { responsePair ->
                    val response = responsePair.first
                    val result = responsePair.second
                    val body = result.component1()
                    var stringResponse: StringResponse? = null

                    Log.e("PUTA", String.format("El result es: %s", body))
                    Log.e("PUTA", String.format("El response es: %s", response));


                    result.fold(success = {
                        stringResponse = StringResponse(body, true)
                    }, failure = {
                        stringResponse = StringResponse(body, false)
                    })

                    response.dataStream.close()
                    stringResponse!!
                }.toObservable()
    }

    private fun formattedQueries(packageToSend: Package): List<Pair<String, Any?>> =
            buildListOfPairs(packageToSend.queries)

    private fun formDataBody(packageToSend: Package): List<Pair<String, Any?>> =
            buildListOfPairs(packageToSend.payload)

    private fun buildListOfPairs(map: Map<String, Any?>): List<Pair<String, Any?>>{

        val result: MutableList<Pair<String, Any?>> = mutableListOf()

        map.entries.forEach {
            val pair = Pair(it.key, it.value)
            result.add(pair)
        }

        return result
    }

}