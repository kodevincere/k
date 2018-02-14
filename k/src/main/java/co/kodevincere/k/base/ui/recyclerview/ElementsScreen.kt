package co.kodevincere.k.base.ui.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by mE on 2/12/18.
 */
interface ElementsScreen<I, L: List<I>, A : BaseRecyclerAdapter<I, L>>: ElementsScreenViewModel<L>,
        BaseRecyclerView.BaseRecyclerViewActionListener  {

    var adapter: A
    var recycler: BaseRecyclerView?

    override fun clearAll(){
        adapter.clearAll()
    }

    override fun stopRefreshing(){
        recycler?.stopRefreshing()
    }

    override fun setItems(items: L){
        recycler?.stopRefreshing()
        adapter.setItems(items)
    }

    override fun appendedItems(count: Int) {
        adapter.appendedItems(count)
        recycler?.stopRefreshing()
    }

    override fun updateItemAt(position: Int) {
        adapter.updateItemAt(position)
    }

    override fun removeItemAtPosition(position: Int) {
        adapter.removeItemAt(position)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(viewContext())

    fun configureLayoutManager(): RecyclerView.LayoutManager{
        val layoutManager = getLayoutManager()

        when(layoutManager){
        //GridSpanSize calculated from here and Staggered calculated in ViewHolderLoading
            is GridLayoutManager -> configureGridLayoutManager(layoutManager)
        }

        return layoutManager
    }

    fun configureGridLayoutManager(layoutManager: GridLayoutManager) {

        val spanCount = layoutManager.spanCount
        val adapter = recycler?.adapter as? A

        val a = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {

                if(adapter == null){
                    return 0
                }

                var shouldShowLoading = position == adapter.loaderPositionInList() - 1

                return if(shouldShowLoading && adapter.loadingView){
                    spanCount
                }else{
                    1
                }
            }
        }
        layoutManager.spanSizeLookup = a
    }

    fun initRecycler() {
//        recycler = getBaseRecycler()
        recycler?.adapter = adapter
        recycler?.actionListener = this
        recycler?.layoutManager = configureLayoutManager()

//        adapter.actionListener = presenter
    }

}