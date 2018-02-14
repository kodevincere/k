package co.kodevincere.example.ui.detail.changename

import android.os.Bundle
import co.kodevincere.k.base.ui.BaseFragment
import co.kodevincere.k.base.ui.BaseScreenViewModel

/**
 * Created by mE on 2/13/18.
 */
interface ViewModelChangeName: BaseScreenViewModel {

    fun showName(name: String)
    fun saveExtras(clazz: Class<out BaseFragment<*>>, bundle: Bundle)

}