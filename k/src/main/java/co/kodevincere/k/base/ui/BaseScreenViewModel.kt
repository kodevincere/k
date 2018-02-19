package co.kodevincere.k.base.ui

import android.content.Intent
import android.os.Bundle

/**
 * Created by mE on 2/1/18.
 */
interface BaseScreenViewModel: BaseViewModel {

    fun goUp()
    fun goBack()
    fun finish()
    fun showMessage(message: String)
    fun showMessageAndNotifyOnDismiss(message: String, messageCode: Int)
    fun showActivity(clazz: Class<*>, bundle: Bundle? = null)
    fun showActivityForResult(clazz: Class<*>, bundle: Bundle? = null, requestCode: Int)
    fun returnResult(resultCode: Int, intent: Intent?)
    fun returnResultAndFinish(resultCode: Int, intent: Intent?)

}