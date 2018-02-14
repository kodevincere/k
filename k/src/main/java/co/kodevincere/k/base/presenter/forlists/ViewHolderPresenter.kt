package co.kodevincere.k.base.presenter.forlists

import co.kodevincere.k.base.presenter.BasePresenter
import co.kodevincere.k.base.ui.recyclerview.ViewHolderViewModel

/**
 * Created by mE on 2/12/18.
 */
interface ViewHolderPresenter<I, VHVM: ViewHolderViewModel>: BasePresenter<VHVM> {

    var item: I?

    fun processItem(item: I?){
        this.item = item
    }

    override fun canReduceMemoryUsage(hashCode: Int) {
        super.canReduceMemoryUsage(hashCode)
        item = null
    }

}