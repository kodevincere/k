package co.kodevincere.example.ui.main

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import co.kodevincere.example.R
import co.kodevincere.example.domain.models.User
import co.kodevincere.example.ui.main.recyclerview.AdapterMain
import co.kodevincere.k.base.ui.recyclerview.BaseRecyclerView
import co.kodevincere.k.base.ui.recyclerview.ElementsActivity
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

//class MainActivity : ElementsActivity<User, MutableList<User>, AdapterMain, PresenterMain>(), ViewModelMain{
class MainActivity : ElementsActivity<User, RealmResults<User>, AdapterMain, PresenterMain>(), ViewModelMain{

    override var adapter = AdapterMain()

    override var recycler: BaseRecyclerView? = null
        get() = rvUsers

    companion object {

        const val KEY_TEST_STRING = "testString"

        fun createDetailResultIntent(testString: String?): Intent{
            val intent = Intent()

            intent.putExtra(KEY_TEST_STRING, testString)

            return intent
        }
    }

    override fun bindToPresenter() {
        presenter?.bindViewModel(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.insertLoading()
    }

    override fun getViewLayout(): Int = R.layout.activity_main

    override fun getPresenterKey(): String = co.kodevincere.example.domain.PresenterPool.KEY_PRESENTER_MAIN

    override fun getLayoutManager(): RecyclerView.LayoutManager =
            GridLayoutManager(viewContext(), 3, RecyclerView.VERTICAL, false)

}