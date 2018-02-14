package co.kodevincere.k.base.presenter

import co.kodevincere.k.base.networking.PostOfficeConnectivityChecker
import co.kodevincere.k.base.ui.BaseViewModel

/**
 * Created by mE on 2/1/18.
 */
open interface BasePresenter<V: BaseViewModel> {

    var viewModel: V?

    fun bindViewModel(viewModel: V){
        this.viewModel = viewModel
    }

    fun canReduceMemoryUsage(hashCode: Int){
        if(viewModel?.hashCode() == hashCode){
            viewModel = null
        }
    }

    fun removedFromCache(){

    }

    fun isConnected(): Boolean = PostOfficeConnectivityChecker.isConnected()

}