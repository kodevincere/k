package co.kodevincere.k.base.presenter

import android.content.Intent
import android.os.Bundle
import co.kodevincere.k.base.BaseApp
import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.JSONResponse
import co.kodevincere.k.base.networking.responses.JaSONResponse
import co.kodevincere.k.base.ui.BaseFragment
import co.kodevincere.k.base.ui.BaseScreenViewModel
import co.kodevincere.k.base.ui.ParentScreen
import io.reactivex.Observable

/**
 * Created by mE on 1/31/18.
 */
interface BaseScreenPresenter<V: BaseScreenViewModel>: BasePresenter<V> {

    fun onCreate(){

    }

    fun onResume(){

    }

    fun processBundle(bundle: Bundle){

    }

    fun onStart(){

    }

    fun onPause(){

    }

    fun onStop(){

    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun finish(){
        viewModel?.finish()
    }

    fun willGoBack(up: Boolean) {
        if(up)
            viewModel?.goUp()
        else
            viewModel?.goBack()
    }

    fun showFragment(baseFragment: BaseFragment<*>, addToStack: Boolean){
        if(viewModel is ParentScreen){
            (viewModel as? ParentScreen)?.showFragment(baseFragment, addToStack)
        }else if(viewModel is BaseFragment<*>){
            (viewModel as? BaseFragment<*>)?.showFragment(baseFragment, addToStack)
        }
    }

    fun showActivity(clazz: Class<*>, bundle: Bundle? = null){
        viewModel?.showActivity(clazz, bundle)
    }

    fun showActivityForResult(clazz: Class<*>, bundle: Bundle? = null, requestCode: Int){
        viewModel?.showActivityForResult(clazz, bundle, requestCode)
    }

    //Networking
    fun rxSendPackageWaitJSON(packageToSend: Package): Observable<JSONResponse> =
            BaseApp.sInstance.postOffice.rxSendPackageWaitJSON(packageToSend)

    fun rxSendPackageWaitJaSON(packageToSend: Package): Observable<JaSONResponse> =
            BaseApp.sInstance.postOffice.rxSendPackageWaitJaSON(packageToSend)


}