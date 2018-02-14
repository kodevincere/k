package co.kodevincere.k.base.presenter.forlists

import co.kodevincere.k.base.presenter.BaseScreenPresenter
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener

/**
 * Created by mE on 2/6/18.
 */
interface ElementsPresenter<I, L: List<I>, VM: ElementsScreenViewModel<L>>: BaseScreenPresenter<VM>,
        ListActionListener<I> {

    var itemList: L

    fun initItemList(): L
    fun clearAllItemList()
    fun appendToItemList(newItems: L?)
    fun removeItemAtPosition(position: Int)

    //BaseScreenPresenter
    override fun onCreate() {
        super.onCreate()
        itemList = initItemList()
    }

    //BaseScreenPresenter
    override fun bindViewModel(viewModel: VM) {
        super.bindViewModel(viewModel)
        setItems()
    }

    //ListActionListener
    override fun handleListAction(goodOldActionPack: GoodOldActionPack<I>) {

    }

    fun refresh(){
    }

    fun canRequestNext(): Boolean = false

    fun requestNext(): Boolean = false

    fun clearAll(){
        clearAllItemList()
        viewModel?.clearAll()
    }

    fun setItems(){
        viewModel?.setItems(itemList)
    }

    fun getItemPosition(item: I): Int{
        return itemList.indexOf(item)
    }

    fun appendToItemsAndRefreshInterface(newItems: L? = null, count: Int = 0){
        appendToItemList(newItems)
        viewModel?.appendedItems(newItems?.count() ?: count)
    }

    fun updateItemAtPosition(position: Int){
        viewModel?.updateItemAt(position)
    }

    fun removeFromItemsAndRefreshInterface(itemPosition: Int){
        viewModel?.removeItemAtPosition(itemPosition)
        removeItemAtPosition(itemPosition)
    }

}