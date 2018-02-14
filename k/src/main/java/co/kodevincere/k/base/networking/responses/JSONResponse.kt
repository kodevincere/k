package co.kodevincere.k.base.networking.responses

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by mE on 2/1/18.
 */
class JSONResponse(body: String?, success: Boolean) : DeliveryResponse<JSONObject>(body, success) {

    override fun createResponse(response: String?): JSONObject {
        var jsonObject = JSONObject()

        try{
            jsonObject = JSONObject(response)
        }catch (e: JSONException){
            jsonObject.put(KEY_CUSTOM_ERROR, response)
        }

        return jsonObject
    }

}