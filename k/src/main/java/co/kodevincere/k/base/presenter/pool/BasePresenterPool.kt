package co.kodevincere.k.base.presenter.pool

import android.util.LruCache
import co.kodevincere.k.base.presenter.BasePresenter
import co.kodevincere.k.base.ui.BaseViewModel

/**
 * Created by mE on 2/1/18.
 */
abstract class BasePresenterPool protected constructor() {
    private val presenterCache: InnerLruCache


    init {
        presenterCache = InnerLruCache(MAX_SIZE)
    }

    fun <P : BasePresenter<out BaseViewModel>> getPresenter(key: String): P? {
        return presenterCache.get(key) as? P
    }

    abstract fun create(key: String): BasePresenter<out BaseViewModel>?

    fun clearAll(){
        presenterCache.evictAll()
    }

    internal inner class InnerLruCache
    /**
     * @param maxSize for caches that do not override [.sizeOf], this is
     * the maximum number of entries in the cache. For all other caches,
     * this is the maximum sum of the sizes of the entries in this cache.
     */
    (maxSize: Int) : LruCache<String, BasePresenter<out BaseViewModel>>(maxSize) {

        override fun create(key: String): BasePresenter<out BaseViewModel>? = this@BasePresenterPool.create(key)

        override fun entryRemoved(evicted: Boolean, key: String, oldValue: BasePresenter<out BaseViewModel>, newValue: BasePresenter<out BaseViewModel>) {
            super.entryRemoved(evicted, key, oldValue, newValue)
            oldValue.removedFromCache()
        }
    }

    companion object {

        private val MAX_SIZE = 5
    }

}