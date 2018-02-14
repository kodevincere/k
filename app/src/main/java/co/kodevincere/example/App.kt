package co.kodevincere.example

import co.kodevincere.k.base.BaseApp
import co.kodevincere.k.base.networking.postoffices.FuelPostOffice
import co.kodevincere.k.base.networking.postoffices.PostOffice
import co.kodevincere.k.base.presenter.pool.BasePresenterPool
import com.facebook.drawee.backends.pipeline.Fresco
import io.realm.Realm

/**
 * Created by mE on 2/1/18.
 */
class App: BaseApp() {

    companion object {
        fun getInstance(): BaseApp = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Fresco.initialize(this)
    }

    override fun openPostOffice(): PostOffice {
        val postOffice = FuelPostOffice()
        postOffice.zetRootPath("https://randomuser.me")
        return postOffice
    }

    override fun createPresenterPool(): BasePresenterPool = co.kodevincere.example.domain.PresenterPool()

}