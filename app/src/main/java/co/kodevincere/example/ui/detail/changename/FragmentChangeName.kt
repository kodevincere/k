package co.kodevincere.example.ui.detail.changename

import android.os.Bundle
import co.kodevincere.example.R
import co.kodevincere.k.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_change_name.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by mE on 2/13/18.
 */
class FragmentChangeName: BaseFragment<PresenterChangeName>(), ViewModelChangeName {

    companion object {

        const val KEY_USER_ID = "userId"

        fun createBundle(userId: String): Bundle {
            val bundle = Bundle()

            bundle.putString(KEY_USER_ID, userId)

            return bundle
        }

        @JvmStatic
        fun newInstance(bundle: Bundle?): FragmentChangeName{
            val fragmentDetail = FragmentChangeName()
            fragmentDetail.arguments = bundle

            return fragmentDetail
        }

    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_change_name
    }

    override fun bindToPresenter() {
        presenter?.bindViewModel(this)
    }

    override fun getPresenterKey(): String {
        return co.kodevincere.example.domain.PresenterPool.KEY_PRESENTER_CHANGE_NAME
    }

    override fun initViews() {
        super.initViews()
        btnChange.onClick {
            presenter?.onChangeClicked(etName.text.toString())
        }
    }

    override fun showName(name: String) {
        etName.setText(name)
    }

}