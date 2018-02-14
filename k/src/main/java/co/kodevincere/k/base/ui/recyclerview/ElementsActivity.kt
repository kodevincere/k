package co.kodevincere.k.base.ui.recyclerview

import co.kodevincere.k.base.presenter.forlists.ElementsPresenter
import co.kodevincere.k.base.ui.BaseActivity

/**
 * Created by mE on 2/6/18.
 */
abstract class ElementsActivity<I, L: List<I>, A : BaseRecyclerAdapter<I, L>,
        P : ElementsPresenter<I, L, out ElementsScreenViewModel<L>>> : BaseActivity<P>(),
        ElementsScreen<I, L, A>, ElementsScreenViewModel<L>, BaseRecyclerView.BaseRecyclerViewActionListener {

    override fun initViews() {
        super.initViews()
        initRecycler()
        adapter.actionListener = presenter
    }

    override fun onRefresh() {
        presenter?.refresh()
    }

    override fun reloadData() {
        adapter.notifyDataSetChanged()
    }

    override fun canRequestNext(): Boolean {
        return presenter?.canRequestNext() == true
    }

    override fun requestNext(): Boolean {
        return presenter?.requestNext() == true
    }

}