package co.kodevincere.k.base.ui.recyclerview

import android.view.View
import co.kodevincere.k.base.presenter.forlists.ViewHolderPresenter
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener

/**
 * Created by mE on 2/12/18.
 */

abstract class BaseViewHolderWithPresenter<I, VHVM: ViewHolderViewModel,
        P: ViewHolderPresenter<I, VHVM>>(itemView: View?, actionListener: ListActionListener<I>?) :
        BaseViewHolder<I>(itemView, actionListener){

    protected var presenter: P? = null

    abstract fun bindToPresenter()
    abstract fun startPresenter(): P

    override fun bindItem(item: I?) {
        super.bindItem(item)

        if(presenter == null){
            presenter = startPresenter()
        }else{
            presenter?.canReduceMemoryUsage(hashCode())
        }

        bindToPresenter()
        presenter?.processItem(item)
    }

}