package co.kodevincere.k.base.networking.packages

import org.json.JSONObject

/**
 * Created by mE on 2/1/18.
 */
class FuelPackage(address: String) : Package(address) {

    override fun buildJSONBody() = JSONObject(payload)

}