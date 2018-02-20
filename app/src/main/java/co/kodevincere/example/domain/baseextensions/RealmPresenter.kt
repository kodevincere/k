package co.kodevincere.example.domain.baseextensions

import co.kodevincere.k.base.presenter.forlists.ElementsPresenter
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.deleteFromRealm

/**
 * Created by mE on 2/9/18.
 */
abstract class RealmPresenter<I: RealmModel, VM: ElementsScreenViewModel<RealmResults<I>>>: ElementsPresenter<I, RealmResults<I>, VM>() {

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

    open protected fun getRealmData(): RealmQuery<I> = realm.where(getObjectClass())

    open protected fun willSort(): Boolean = false

    open protected fun getSortKey(): String = ""

    open protected fun getSortOrder(): Sort = Sort.ASCENDING
}