package co.kodevincere.k.base.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
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
import io.reactivex.disposables.Disposable

/**
 * Created by mE on 1/31/18.
 */
abstract class BaseScreenPresenter<V: BaseScreenViewModel>: BasePresenter<V>, LifecycleObserver {

    protected val viewDisposables = mutableListOf<Disposable>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(){
        initViewDisposables()
    }

    open fun processBundle(bundle: Bundle){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(){
        clearViewDisposables()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(){
        canReduceMemoryUsage(hashCode())
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    open fun finish(){
        viewModel?.finish()
    }

    open fun willGoBack(up: Boolean) {
        if(up)
            viewModel?.goUp()
        else
            viewModel?.goBack()
    }

    open fun showFragment(baseFragment: BaseFragment<*>, addToStack: Boolean){
        if(viewModel is ParentScreen){
            (viewModel as? ParentScreen)?.showFragment(baseFragment, addToStack)
        }else if(viewModel is BaseFragment<*>){
            (viewModel as? BaseFragment<*>)?.showFragment(baseFragment, addToStack)
        }
    }

    open fun showActivity(clazz: Class<*>, bundle: Bundle? = null){
        viewModel?.showActivity(clazz, bundle)
    }

    open fun showActivityForResult(clazz: Class<*>, bundle: Bundle? = null, requestCode: Int){
        viewModel?.showActivityForResult(clazz, bundle, requestCode)
    }

    open fun messageDismissed(messageCode: Int) {

    }

    open fun initViewDisposables(){

    }

    fun addToViewDisposables(disposable: Disposable?){
        disposable?.let {
            viewDisposables.add(it)
        }
    }

    fun clearViewDisposables(){
        viewDisposables.map { it.dispose() }
        viewDisposables.clear()
    }

    //Networking
    fun rxSendPackageWaitJSON(packageToSend: Package): Observable<JSONResponse> =
            BaseApp.sInstance.postOffice.rxSendPackageWaitJSON(packageToSend)

    fun rxSendPackageWaitJaSON(packageToSend: Package): Observable<JaSONResponse> =
            BaseApp.sInstance.postOffice.rxSendPackageWaitJaSON(packageToSend)


}