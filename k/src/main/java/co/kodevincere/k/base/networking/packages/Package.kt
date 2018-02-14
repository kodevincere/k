package co.kodevincere.k.base.networking.packages

import co.kodevincere.k.base.networking.postoffices.PostOffice.HttpMethod
import co.kodevincere.k.base.networking.postoffices.PostOffice.HttpMethod.GET
import org.json.JSONObject

/**
 * Created by mE on 2/1/18.
 */

abstract class Package(val address: String){

    var method = GET
        private set

    var payload = emptyMap<String, Any>()
        private set

    var headers = emptyMap<String, String>()
        private set

    var queries = emptyMap<String, String>()
        private set

    var addressParams = emptyMap<String, Any>()
        private set

    val finalAddress: String
        get() = buildFinalAddress()

    abstract fun buildJSONBody(): JSONObject

    fun zetMethod(method: HttpMethod): Package {
        this.method = method
        return this
    }

    fun zetPayload(payload: Map<String, Any>): Package {
        this.payload = payload
        return this
    }

    fun zetHeaders(headers: Map<String, String>): Package {
        this.headers = headers
        return this
    }

    fun zetQueries(queries: Map<String, String>): Package {
        this.queries = queries
        return this
    }

    fun zetAddressParams(addressParams: Map<String, Any>): Package {
        this.addressParams = addressParams
        return this
    }

    protected fun buildFinalAddress(): String {
        var newAddress = address

        addressParams.forEach { (key, value) ->
            newAddress = newAddress.replace("{$key}", "$value")
        }

        return newAddress
    }

}