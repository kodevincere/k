package co.kodevincere.k.base.presenter.forlists.withrequests

import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.JaSONResponse
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import io.reactivex.Observable

/**
 * Created by mE on 2/9/18.
 */
abstract class JaSONRequestListPresenter<I, VM: ElementsScreenViewModel<MutableList<I>>>: RequestListPresenter<I, JaSONResponse, VM>() {

    //RequestElementsPresenter
    override fun makeRequest(packageToSend: Package): Observable<JaSONResponse> =
            rxSendPackageWaitJaSON(packageToSend)

}