package co.kodevincere.k.base.networking.postoffices

import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.packages.StringResponse
import co.kodevincere.k.base.networking.responses.JSONResponse
import co.kodevincere.k.base.networking.responses.JaSONResponse
import io.reactivex.Observable

/**
 * Created by mE on 2/1/18.
 */
abstract class PostOffice{

    var rootPath = ""
        private set
    var headers = mutableMapOf<String, String>()
        private set

    protected abstract fun internalSetRootPath(rootPath: String)

    protected abstract fun internalAddPermanentHeaders(key: String, value: String)

    protected abstract fun internalRemovePermanentHeaders(key: String, value: String)

    protected abstract fun rxInternalSendPackage(packageToSend: Package, jsonBody: Boolean = true): Observable<StringResponse>

    fun zetRootPath(rootPath: String){
        this.rootPath = rootPath
    }

    fun addPermanentHeader(key: String, value: String){
        headers.put(key, value)
        internalAddPermanentHeaders(key, value)
    }

    fun removePermanentHeader(key: String, value: String){
        headers.remove(key)
        internalRemovePermanentHeaders(key, value)
    }

    fun rxSendPackageWaitJSON(packageToSend: Package, jsonBody: Boolean = true): Observable<JSONResponse>{
        return rxInternalSendPackage(packageToSend, jsonBody)
                .map { response ->
                    JSONResponse(response.body, response.success)
                }
    }

    fun rxSendPackageWaitJaSON(packageToSend: Package, jsonBody: Boolean = true): Observable<JaSONResponse>{
        return rxInternalSendPackage(packageToSend, jsonBody)
                .map { response ->
                    JaSONResponse(response.body, response.success)
                }
    }

    enum class HttpMethod {
        GET,
        PUT,
        POST,
        PATCH,
        DELETE
    }
}