package co.kodevincere.example.ui.main.recyclerview

import co.kodevincere.k.base.presenter.forlists.ViewHolderPresenter
import co.kodevincere.example.domain.models.User

/**
 * Created by mE on 2/12/18.
 */

class PresenterViewHolderMain: ViewHolderPresenter<User, ViewHolderViewModelMain> {

    override var item: User? = null

    override var viewModel: ViewHolderViewModelMain? = null

    fun onRootClicked() {
        viewModel?.dispatchClick(ViewHolderMain.KEY_ACTION_ID_ROOT)
    }

    fun onProfilePictureClicked() {
        viewModel?.dispatchClick(ViewHolderMain.KEY_ACTION_ID_PROFILE_PICTURE)
    }


}