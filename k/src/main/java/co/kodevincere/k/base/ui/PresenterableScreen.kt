package co.kodevincere.k.base.ui

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import co.kodevincere.k.base.BaseApp
import co.kodevincere.k.base.presenter.BaseScreenPresenter
import io.reactivex.disposables.Disposable

/**
 * Created by mE on 2/12/18.
 */
interface PresenterableScreen<P: BaseScreenPresenter<out BaseScreenViewModel>>: BaseScreenViewModel{

    var presenter: P?
    var viewDisposables: MutableList<Disposable>?

    fun initializeAll(extras: Bundle?){
        initViews()
        extras?.let {
            presenter?.processBundle(it)
        }
    }

    fun getViewLayout(): Int

    fun bindToPresenter()

    fun getPresenterKey(): String

    fun getLifeCycleObject(): Lifecycle

    fun initViews(){
        initViewDisposables()
    }

    fun initViewDisposables(){

    }

    fun addToViewDisposables(disposable: Disposable?){
        disposable?.let {
            viewDisposables?.add(it)
        }
    }

    fun clearViewDisposables(){
        viewDisposables?.map { it.dispose() }
        viewDisposables?.clear()
    }

    fun getOneView(): View?

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
        val presenter: P? = BaseApp.sInstance.presenterPool.getPresenter(getPresenterKey())
        if(presenter != null) {
            getLifeCycleObject().addObserver(presenter)
        }
        return presenter
    }

    fun createIntentForActivityStart(clazz: Class<*>, bundle: Bundle?): Intent {
        val i = Intent(viewContext(), clazz)
        if(bundle != null) {
            i.putExtras(bundle)
        }

        return i
    }

    fun createSnackbar(message: String): Snackbar? {
        val view = getOneView()
        return if(view == null) null else Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    }

    override fun showMessage(message: String) {
        createSnackbar(message)?.show()
    }

    override fun showMessageAndNotifyOnDismiss(message: String, messageCode: Int) {

        createSnackbar(message)?.addCallback(object: Snackbar.Callback(){
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                presenter?.messageDismissed(messageCode)
            }
        })?.show()

    }
}