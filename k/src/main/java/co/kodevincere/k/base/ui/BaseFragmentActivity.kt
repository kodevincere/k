package co.kodevincere.k.base.ui

import android.annotation.SuppressLint
import android.os.Bundle
import co.kodevincere.k.base.presenter.BaseScreenPresenter

/**
 * Created by mE on 2/13/18.
 */
abstract class BaseFragmentActivity<P: BaseScreenPresenter<out BaseScreenViewModel>>: BaseActivity<P>(), ParentScreen {

    protected var activeFragment: BaseFragment<*>? = null
    protected val savedExtras: MutableMap<String, Bundle> = HashMap()

    override fun extrasForMe(extrasId: String): Bundle? {
        return savedExtras[extrasId]
    }

    override fun saveExtras(clazz: Class<out BaseFragment<*>>, bundle: Bundle) {
        savedExtras[clazz.canonicalName] = bundle
    }

    override fun showFragment(baseFragment: BaseFragment<*>, addToStack: Boolean) {
        changeFragment(baseFragment, addToStack)
    }

    @SuppressLint("ResourceType")
    private fun changeFragment(baseFragment: BaseFragment<*>, addToStack: Boolean) {
        val fragmentTag = "${baseFragment.hashCode()}"
        try{
            val transaction = supportFragmentManager?.beginTransaction()
                    ?.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                            android.R.anim.fade_in, android.R.anim.slide_out_right)

            if(addToStack) {
                transaction?.addToBackStack(fragmentTag)
            }

            transaction
                    ?.replace(fragmentContainer(), baseFragment, fragmentTag)
                    ?.commit()

            activeFragment = baseFragment

        }catch (e: Exception){
            e.printStackTrace()
        }

    }

}