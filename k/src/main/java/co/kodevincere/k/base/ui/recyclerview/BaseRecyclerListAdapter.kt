package co.kodevincere.k.base.ui.recyclerview

/**
 * Created by mE on 2/6/18.
 */
abstract class BaseRecyclerListAdapter<I>: BaseRecyclerAdapter<I, MutableList<I>>() {

    override fun clearList() {
        itemList?.clear()
    }

}