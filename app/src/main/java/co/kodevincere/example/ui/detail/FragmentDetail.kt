package co.kodevincere.example.ui.detail

import android.os.Bundle
import co.kodevincere.example.R
import co.kodevincere.example.domain.medias.images.show
import co.kodevincere.k.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by mE on 2/13/18.
 */

class FragmentDetail: BaseFragment<PresenterDetail>(), ViewModelDetail {

    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_ACTION = "action"
        const val KEY_LAST_NAME = "lastName"
        const val KEY_PICTURE_URL = "pictureUrl"

        const val VALUE_ACTION_SHOW = 0
        const val VALUE_ACTION_UPDATE = 1

        fun createShowBundle(id: String?, name: String?, lastName: String?, pictureUrl: String?): Bundle {
            val bundle = Bundle()

            bundle.putString(KEY_ID, id)
            bundle.putString(KEY_NAME, name)
            bundle.putString(KEY_LAST_NAME, lastName)
            bundle.putInt(KEY_ACTION, VALUE_ACTION_SHOW)
            bundle.putString(KEY_PICTURE_URL, pictureUrl)

            return bundle
        }

        fun createUpdateBundle(id: String?, name: String?): Bundle {
            val bundle = Bundle()

            bundle.putString(KEY_ID, id)
            bundle.putString(KEY_NAME, name)
            bundle.putInt(KEY_ACTION, VALUE_ACTION_UPDATE)

            return bundle
        }

        @JvmStatic
        fun newInstance(bundle: Bundle?): FragmentDetail{
            val fragmentDetail = FragmentDetail()
            fragmentDetail.arguments = bundle

            return fragmentDetail
        }
    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_detail
    }

    override fun bindToPresenter() {
        presenter?.bindViewModel(this)
    }

    override fun getPresenterKey(): String = co.kodevincere.example.domain.PresenterPool.KEY_PRESENTER_DETAIL

    override fun initViews() {
        super.initViews()
        setClickListeners()
    }

    override fun showName(name: String?) {
        tvName.text = name
    }

    override fun showLastName(lastName: String?) {
        tvLastName.text = lastName
    }

    override fun showPicture(pictureUrl: String?) {
        ivPicture.show(pictureUrl)
    }

    private fun setClickListeners() {
        btnTesting.onClick {
            presenter?.testingClicked(tvTesting?.text.toString())
        }

        btnChange.onClick {
            presenter?.changeClicked()
        }
    }

}