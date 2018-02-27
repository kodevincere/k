package co.kodevincere.example.ui.detail

import android.os.Bundle
import co.kodevincere.k.R
import co.kodevincere.k.base.presenter.BaseScreenPresenter
import co.kodevincere.k.base.ui.BaseActivity
import co.kodevincere.k.base.ui.BaseScreenViewModel

/**
 * Created by mE on 2/8/18.
 */
class ActivityDetail: BaseActivity<BaseScreenPresenter<BaseScreenViewModel>>(){

    override var presenter = startPresenter()

    override fun getViewLayout(): Int = R.layout.activity_a_fragment_container

    override fun bindToPresenter() {

    }

    override fun getPresenterKey(): String = ""

    override fun fragmentContainer(): Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeButtonEnabled(true)

        showFragment(FragmentDetail.newInstance(intent?.extras), false)
    }


}