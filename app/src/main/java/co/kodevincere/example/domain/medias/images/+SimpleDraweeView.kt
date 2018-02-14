package co.kodevincere.example.domain.medias.images

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by mE on 2/2/18.
 */
fun SimpleDraweeView.show(url: String?, tapToRetry: Boolean = false,
                          autoplayAnimations: Boolean = false){

    if(url == null) return

    val uri = Uri.parse(url)

    val request = ImageRequestBuilder.newBuilderWithSource(uri)
            .setProgressiveRenderingEnabled(true)
            .build()

    val draweeController = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setTapToRetryEnabled(tapToRetry)
            .setOldController(controller)
            .setImageRequest(request)
            .setAutoPlayAnimations(autoplayAnimations)
            .build()

    controller = draweeController

}