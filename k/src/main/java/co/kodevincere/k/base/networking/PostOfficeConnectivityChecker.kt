package co.kodevincere.k.base.networking

import android.content.Context
import android.net.ConnectivityManager
import co.kodevincere.k.base.BaseApp


/**
 * Created by mE on 2/6/18.
 */

class PostOfficeConnectivityChecker{

    companion object {
        @JvmStatic
        fun isConnected(): Boolean{
            val cm = BaseApp.sInstance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo

            return activeNetwork?.isConnectedOrConnecting ?: false
        }
    }

}