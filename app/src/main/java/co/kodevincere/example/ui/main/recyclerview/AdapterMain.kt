package co.kodevincere.example.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import co.kodevincere.example.R
import co.kodevincere.example.domain.baseextensions.BaseRecyclerRealmAdapter
import co.kodevincere.example.domain.models.User
import co.kodevincere.k.base.ui.recyclerview.BaseViewHolder

/**
 * Created by mE on 2/2/18.
 */
class AdapterMain: BaseRecyclerRealmAdapter<User>() {
//class AdapterMain: BaseRecyclerListAdapter<User>() {

    override fun makeViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?, viewType: Int): BaseViewHolder<User> =
            ViewHolderMain(layoutInflater.inflate(R.layout.item_user, parent, false),
                    actionListener)

}