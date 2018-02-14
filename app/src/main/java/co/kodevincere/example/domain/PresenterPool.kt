package co.kodevincere.example.domain

import co.kodevincere.example.ui.detail.PresenterDetail
import co.kodevincere.example.ui.detail.changename.PresenterChangeName
import co.kodevincere.example.ui.main.PresenterMain
import co.kodevincere.k.base.presenter.BasePresenter
import co.kodevincere.k.base.presenter.pool.BasePresenterPool
import co.kodevincere.k.base.ui.BaseViewModel

/**
 * Created by mE on 2/1/18.
 */
class PresenterPool: BasePresenterPool() {

    companion object {
        const val KEY_PRESENTER_MAIN = "PresenterMain"
        const val KEY_PRESENTER_DETAIL = "PresenterDetail"
        const val KEY_PRESENTER_CHANGE_NAME = "PresenterChangeName"
    }

    override fun create(key: String): BasePresenter<out BaseViewModel>? = createPresenterByKey(key)

    private fun createPresenterByKey(key: String): BasePresenter<out BaseViewModel>? = when(key){
        KEY_PRESENTER_MAIN -> PresenterMain()
        KEY_PRESENTER_DETAIL -> PresenterDetail()
        KEY_PRESENTER_CHANGE_NAME -> PresenterChangeName()
        else -> null
    }


}