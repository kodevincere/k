package co.kodevincere.k.base.ui

import android.os.Bundle

/**
 * Created by mE on 2/13/18.
 */

interface ParentScreen: BaseScreenViewModel{

    fun fragmentContainer(): Int
    fun extrasForMe(extrasId: String): Bundle?
    fun saveExtras(clazz: Class<out BaseFragment<*>>, bundle: Bundle)
    fun showFragment(baseFragment: BaseFragment<*>, addToStack: Boolean)

}