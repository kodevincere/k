package co.kodevincere.k.base

import android.app.Application
import co.kodevincere.k.base.networking.postoffices.PostOffice
import co.kodevincere.k.base.presenter.pool.BasePresenterPool

/**
 * Created by mE on 2/1/18.
 */
abstract class BaseApp: Application() {

    companion object {
        lateinit var sInstance: BaseApp
    }

    var postOffice: PostOffice = openPostOffice()
    var presenterPool: BasePresenterPool = createPresenterPool()

    protected abstract fun openPostOffice(): PostOffice
    protected abstract fun createPresenterPool(): BasePresenterPool

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

}