package co.kodevincere.k.base.presenter.forlists.withrequests

import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.DeliveryResponse
import co.kodevincere.k.base.presenter.forlists.ElementsPresenter
import co.kodevincere.k.base.ui.recyclerview.ElementsScreenViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by mE on 2/6/18.
 */
abstract class RequestElementsPresenter<I, L: List<I> , R: DeliveryResponse<*>, VM: ElementsScreenViewModel<L>>: ElementsPresenter<I, L, VM> {

    override var itemList: L = initItemList()

    var nextQuery = ""
    var isMakingRequest = false
    var newItems = mutableListOf<I>()

    abstract fun getElementPackage(): Package
    abstract fun handleResponse(response: R): Observable<I>
    abstract fun makeRequest(packageToSend: Package): Observable<R>

    protected fun makeFullRequest(){

        newItems = mutableListOf()

        isMakingRequest = true

        makeRequest(getElementPackage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    response: R ->
                    extractNextQuery(response)
                    handleResponse(response)
                }
                .doOnNext {
                    element: I ->
                    addElementToNewList(element)
                }
                .doOnComplete {
                    fullRequestCompleted()
                    appendNewItemsToItemList()
                }
                .subscribe()

    }

    //ElementsPresenter
    override fun refresh() {
        super.refresh()
        restartState()
        clearAll()
        makeFullRequest()
    }

    override fun canRequestNext(): Boolean = canMakeRequest() && nextQuery.isNotBlank()

    protected open fun canMakeRequest(): Boolean = isConnected() && !isMakingRequest

    protected open fun extractNextQuery(jsonResponse: R): String = ""

    protected open fun addElementToNewList(element: I) {
        newItems.add(element)
    }

    protected open fun appendNewItemsToItemList() {

    }

    protected open fun fullRequestCompleted() {
        isMakingRequest = false
    }

    private fun restartState() {
        nextQuery = ""
    }

}