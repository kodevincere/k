package co.kodevincere.example.domain.baseextensions

import co.kodevincere.k.base.ui.recyclerview.BaseRecyclerAdapter
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Created by mE on 2/9/18.
 */
abstract class BaseRecyclerRealmAdapter<I: RealmModel>: BaseRecyclerAdapter<I, RealmResults<I>>() {

    override fun clearList() {

    }

}