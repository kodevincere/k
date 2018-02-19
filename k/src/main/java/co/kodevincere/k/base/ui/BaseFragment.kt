package co.kodevincere.k.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kodevincere.k.base.BaseApp
import co.kodevincere.k.base.presenter.BaseScreenPresenter

/**
 * Created by mE on 2/13/18.
 */
abstract class BaseFragment<P: BaseScreenPresenter<out BaseScreenViewModel>>: Fragment()
        , PresenterableScreen<P> {

    override var presenter: P? = startPresenter()

    protected var parentScreen: ParentScreen? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ParentScreen){
            parentScreen = context
        }
    }

    override fun viewContext(): Context {
        return context ?: BaseApp.sInstance
    }

    override fun getOneView(): View? {
        return view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(getViewLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAll(myExtras())
    }

    override fun onResume() {
        super.onResume()
        bindToPresenter()
        presenter?.onResume()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStart()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.canReduceMemoryUsage(hashCode())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter?.onActivityResult(requestCode, resultCode, data)
    }

    override fun goUp() {
        parentScreen?.goUp()
    }

    override fun goBack() {
        parentScreen?.goBack()
    }

    override fun finish() {
        parentScreen?.finish()
    }

    override fun showActivity(clazz: Class<*>, bundle: Bundle?) {
        val i = createIntentForActivityStart(clazz, bundle)
        startActivity(i)
    }

    override fun showActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int) {
        val i = createIntentForActivityStart(clazz, bundle)
        startActivityForResult(i, requestCode)
    }

    override fun returnResult(resultCode: Int, intent: Intent?) {
        parentScreen?.returnResult(resultCode, intent)
    }

    override fun returnResultAndFinish(resultCode: Int, intent: Intent?) {
        parentScreen?.returnResultAndFinish(resultCode, intent)
    }

    fun saveExtras(clazz: Class<out BaseFragment<*>>, bundle: Bundle){
        parentScreen?.saveExtras(clazz, bundle)
    }

    fun showFragment(baseFragment: BaseFragment<*>, addToStack: Boolean) {
        parentScreen?.showFragment(baseFragment, addToStack)
    }

    protected fun myExtras(): Bundle?{
        var extras = parentScreen?.extrasForMe(extrasId())
        if(extras == null){
            extras  = arguments
        }
        return extras
    }

    fun extrasId() = this::class.java.canonicalName

}