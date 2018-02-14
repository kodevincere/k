package co.kodevincere.k.base.networking.packages

import co.kodevincere.k.base.networking.responses.DeliveryResponse

/**
 * Created by mE on 2/1/18.
 */
class StringResponse(body: String?, success: Boolean) : DeliveryResponse<String>(body, success) {
    override fun createResponse(response: String?): String = response ?: ""
}