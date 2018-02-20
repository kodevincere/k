package co.kodevincere.k.base.presenter.forlists

import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel

/**
 * Created by mE on 2/6/18.
 */
abstract class ListPresenter<I, VM: ElementsScreenViewModel<MutableList<I>>>: ElementsPresenter<I, MutableList<I>, VM>() {

    //ElementsPresenter
    override fun initItemList(): MutableList<I> = mutableListOf()

    //ElementsPresenter
    override fun clearAllItemList() {
        itemList.clear()
    }

    //ElementsPresenter
    override fun appendToItemList(newItems: MutableList<I>?) {
        newItems?.let{
            itemList.addAll(it)
        }
    }

    //ElementsPresenter
    override fun removeItemAtPosition(position: Int) {
        itemList.removeAt(position)
    }

}