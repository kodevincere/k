package co.kodevincere.example.domain.baseextensions

import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.JaSONResponse
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import io.reactivex.Observable
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by mE on 2/9/18.
 */
abstract class JaSONRequestRealmPresenter<I: RealmObject, VM: ElementsScreenViewModel<RealmResults<I>>>: RequestRealmPresenter<I, JaSONResponse, VM>() {

    override fun makeRequest(packageToSend: Package): Observable<JaSONResponse> =
            rxSendPackageWaitJaSON(packageToSend)

}