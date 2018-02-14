package co.kodevincere.k.base.ui.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.kodevincere.k.R
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener

/**
 * Created by mE on 2/1/18.
 */
abstract class BaseRecyclerAdapter<I, L: List<I>>: RecyclerView.Adapter<BaseViewHolder<I>>() {

    companion object {
        val KEY_VIEW_TYPE_LOADING = 666
        val KEY_VIEW_TYPE_STANDARD = 667
    }

    var loadingView = false
    protected var itemList: L? = null
        private set
    var actionListener: ListActionListener<I>? = null


    abstract fun makeViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup?, viewType: Int): BaseViewHolder<I>

    abstract fun clearList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<I> {
        val layoutInflater = LayoutInflater.from(parent?.context)

        return when(viewType){
            KEY_VIEW_TYPE_LOADING -> ViewHolderLoading(layoutInflater
                    .inflate(R.layout.item_loading, parent, false))
            else -> makeViewHolder(layoutInflater, parent, viewType)
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<I>, position: Int) {
        if(getItemViewType(position) == KEY_VIEW_TYPE_LOADING){
            holder.bindItem(null)
        }else{
            holder.bindItem(itemAtPosition(position))
        }
    }

    final override fun getItemCount(): Int = if (!loadingView) size() else size() + 1

    final override fun getItemViewType(position: Int): Int {
        return if(loadingView){
            if (position < size()) viewType(position) else KEY_VIEW_TYPE_LOADING
        }else{
            viewType(position)
        }
    }

    fun size(): Int = itemList?.size ?: 0

    fun loaderPositionInList(): Int = itemCount

    private fun itemAtPosition(position: Int): I? = this.itemList?.get(position)

    fun viewType(position: Int): Int = KEY_VIEW_TYPE_STANDARD

    fun setItems(items: L){
        deleteLoading()
        this.itemList = items
        notifyDataSetChanged()
    }

    fun appendedItems(count: Int){
        deleteLoading()

        val originalSize = size() - count
        notifyItemRangeInserted(originalSize, size())

    }

    fun removeItemAt(position: Int){
        notifyItemRemoved(position)
    }

    fun updateItemAt(position: Int){
        notifyItemChanged(position)
    }

    fun insertLoading() {
        this.loadingView = true
        notifyItemInserted(loaderPositionInList())
    }

    fun deleteLoading() {
        if (loadingView) {
            notifyItemRemoved(loaderPositionInList())
            loadingView = false
        }
    }

    fun clearAll(){
        clearList()
        notifyDataSetChanged()
    }

}