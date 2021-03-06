package co.kodevincere.example.ui.detail

import co.kodevincere.k.base.ui.BaseScreenViewModel
import io.reactivex.subjects.PublishSubject


/**
 * Created by mE on 2/8/18.
 */
interface ViewModelDetail: BaseScreenViewModel {
    var testButtonEnabled: PublishSubject<Boolean>?

    fun showName(name: String?)
    fun showLastName(lastName: String?)
    fun showPicture(pictureUrl: String?)
    fun enableButton()
    fun disableButton()
}