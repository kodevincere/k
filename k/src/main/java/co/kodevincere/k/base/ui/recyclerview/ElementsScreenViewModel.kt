package co.kodevincere.k.base.ui.recyclerview

import co.kodevincere.k.base.ui.BaseScreenViewModel

/**
 * Created by mE on 2/6/18.
 */
interface ElementsScreenViewModel<in L: List<*>>: BaseScreenViewModel {

    fun clearAll()
    fun reloadData()
    fun stopRefreshing()
    fun setItems(items: L)
    fun appendedItems(count: Int)
    fun updateItemAt(position: Int)
    fun removeItemAtPosition(position: Int)

}