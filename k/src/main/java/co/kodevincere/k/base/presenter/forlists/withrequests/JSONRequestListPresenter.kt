package co.kodevincere.k.base.presenter.forlists.withrequests

import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.JSONResponse
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import io.reactivex.Observable

/**
 * Created by mE on 2/9/18.
 */
abstract class JSONRequestListPresenter<I, VM: ElementsScreenViewModel<MutableList<I>>>: RequestListPresenter<I, JSONResponse, VM>() {

    override fun makeRequest(packageToSend: Package): Observable<JSONResponse> =
            rxSendPackageWaitJSON(packageToSend)

}