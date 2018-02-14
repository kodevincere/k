package co.kodevincere.example.ui.detail

import co.kodevincere.k.base.ui.BaseScreenViewModel


/**
 * Created by mE on 2/8/18.
 */
interface ViewModelDetail: BaseScreenViewModel {
    fun showName(name: String?)
    fun showLastName(lastName: String?)
    fun showPicture(pictureUrl: String?)
}