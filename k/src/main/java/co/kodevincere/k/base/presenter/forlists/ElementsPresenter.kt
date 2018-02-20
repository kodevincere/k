package co.kodevincere.k.base.presenter.forlists

import co.kodevincere.k.base.presenter.BaseScreenPresenter
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener

/**
 * Created by mE on 2/6/18.
 */
abstract class ElementsPresenter<I, L: List<I>, VM: ElementsScreenViewModel<L>>: BaseScreenPresenter<VM>(),
        ListActionListener<I> {

    abstract var itemList: L

    abstract fun initItemList(): L
    abstract fun clearAllItemList()
    abstract fun appendToItemList(newItems: L?)
    abstract fun removeItemAtPosition(position: Int)

    //BaseScreenPresenter
    override fun onCreate() {
//        super.onCreate()
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

    open fun refresh(){
    }

    open fun canRequestNext(): Boolean = false

    open fun requestNext(): Boolean = false

    open fun clearAll(){
        clearAllItemList()
        viewModel?.clearAll()
    }

    open fun setItems(){
        viewModel?.setItems(itemList)
    }

    open fun getItemPosition(item: I): Int{
        return itemList.indexOf(item)
    }

    open fun appendToItemsAndRefreshInterface(newItems: L? = null, count: Int = 0){
        appendToItemList(newItems)
        viewModel?.appendedItems(newItems?.count() ?: count)
    }

    open fun updateItemAtPosition(position: Int){
        viewModel?.updateItemAt(position)
    }

    open fun removeFromItemsAndRefreshInterface(itemPosition: Int){
        viewModel?.removeItemAtPosition(itemPosition)
        removeItemAtPosition(itemPosition)
    }

}