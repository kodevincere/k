package co.kodevincere.example.ui.detail.changename

import android.os.Bundle
import co.kodevincere.k.base.presenter.BaseScreenPresenter
import co.kodevincere.example.domain.baseextensions.realm
import co.kodevincere.example.domain.models.User
import co.kodevincere.example.ui.detail.FragmentDetail

/**
 * Created by mE on 2/13/18.
 */
class PresenterChangeName: BaseScreenPresenter<ViewModelChangeName> {

    override var viewModel: ViewModelChangeName? = null

    private var user: User? = null

    override fun processBundle(bundle: Bundle) {
        super.processBundle(bundle)

        val userId = bundle.getString(FragmentChangeName.KEY_USER_ID)

        user = realm.where(User::class.java)
                .equalTo("id", userId)
                .findFirst()
    }

    override fun onResume() {
        super.onResume()
        processUser()
    }

    fun onChangeClicked(newName: String) {
        val bundle = FragmentDetail.createUpdateBundle(user?.id, newName)
        viewModel?.saveExtras(FragmentDetail::class.java, bundle)
        willGoBack(false)
    }

    private fun processUser() {
        viewModel?.showName(user?.name ?: "")
    }

}