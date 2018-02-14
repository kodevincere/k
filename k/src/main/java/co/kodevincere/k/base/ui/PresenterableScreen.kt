package co.kodevincere.k.base.ui

import android.content.Intent
import android.os.Bundle
import co.kodevincere.k.base.BaseApp
import co.kodevincere.k.base.presenter.BaseScreenPresenter

/**
 * Created by mE on 2/12/18.
 */
interface PresenterableScreen<P: BaseScreenPresenter<out BaseScreenViewModel>>: BaseScreenViewModel {

    var presenter: P?

    fun initializeAll(extras: Bundle?){
        initViews()
        extras?.let {
            presenter?.processBundle(it)
        }
        presenter?.onCreate()
    }

    fun getViewLayout(): Int

    fun bindToPresenter()

    fun getPresenterKey(): String

    fun initViews(){

    }

    fun willGoBack(up: Boolean){
        if(presenter != null) {
            presenter?.willGoBack(up)
        }else{
            if(up){
                goUp()
            }else{
                goBack()
            }
        }
    }

    fun startPresenter(): P? {
        return BaseApp.sInstance.presenterPool.getPresenter(getPresenterKey())
    }

    fun createIntentForActivityStart(clazz: Class<*>, bundle: Bundle?): Intent {
        val i = Intent(viewContext(), clazz)
        if(bundle != null) {
            i.putExtras(bundle)
        }

        return i
    }
}