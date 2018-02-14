package co.kodevincere.example.domain.baseextensions

import co.kodevincere.k.base.networking.responses.DeliveryResponse
import co.kodevincere.k.base.presenter.forlists.withrequests.RequestElementsPresenter
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import com.vicpin.krealmextensions.save
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.deleteFromRealm

/**
 * Created by mE on 2/6/18.
 */
abstract class RequestRealmPresenter<I: RealmModel, R: DeliveryResponse<*>, VM: ElementsScreenViewModel<RealmResults<I>>>: RequestElementsPresenter<I, RealmResults<I>, R, VM>() {

    abstract fun getObjectClass(): Class<I>

    //ElementsPresenter
    override fun initItemList(): RealmResults<I> {
        var data = getRealmData()
        if(willSort()){
            data = data.sort(getSortKey(), getSortOrder())
        }
        return data.findAll()
    }

    //ElementsPresenter
    override fun clearAllItemList() {
        writeTransaction {
            itemList.deleteAllFromRealm()
        }
    }

    //ElementsPresenter
    override fun appendToItemList(newItems: RealmResults<I>?) {

    }

    //ElementsPresenter
    override fun removeItemAtPosition(itemPosition: Int) {
        val item = itemList[itemPosition]
        item?.let {
            writeTransaction {
                item.deleteFromRealm()
            }
        }
    }

    //RequestElementsPresenter
    override fun appendNewItemsToItemList() {
        newItems.forEach { it.save() }
        appendToItemsAndRefreshInterface(count = newItems.count())
    }

    open protected fun getRealmData(): RealmQuery<I> = realm.where(getObjectClass())

    open protected fun willSort(): Boolean = false

    open protected fun getSortKey(): String = ""

    open protected fun getSortOrder(): Sort = Sort.ASCENDING

}