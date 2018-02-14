package co.kodevincere.k.base.presenter.forlists.withrequests

import co.kodevincere.k.base.networking.responses.DeliveryResponse
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel

/**
 * Created by mE on 2/6/18.
 */
abstract class RequestListPresenter<I, R: DeliveryResponse<*>, VM: ElementsScreenViewModel<MutableList<I>>>: RequestElementsPresenter<I, MutableList<I>, R, VM>() {

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

    //RequestElementsPresenter
    override fun appendNewItemsToItemList() {
        appendToItemsAndRefreshInterface(newItems)
    }

}