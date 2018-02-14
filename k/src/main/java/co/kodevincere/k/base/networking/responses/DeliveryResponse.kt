package co.kodevincere.k.base.networking.responses

/**
 * Created by mE on 2/1/18.
 */
abstract class DeliveryResponse<B>(body: String?, val success: Boolean = true) {

    companion object {
        val KEY_CUSTOM_ERROR = "customError"
    }

    var body: B? = null

    init {
        this.body = createResponse(body)
    }

    abstract fun createResponse(response: String?): B

}