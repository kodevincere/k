package co.kodevincere.k.base.networking.responses

import co.kodevincere.k.base.utils.json.JaSON
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mE on 2/1/18.
 */
class JaSONResponse(body: String?, success: Boolean) : DeliveryResponse<JaSON>(body, success) {

    override fun createResponse(response: String?): JaSON {

        return try{
            JaSON(response)
        }catch (e: JSONException){
            val json = JSONObject()
            json.put(KEY_CUSTOM_ERROR, response)
            JaSON(json)
        }
    }

}