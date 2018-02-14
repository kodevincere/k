package co.kodevincere.example.domain.networking.packages

import co.kodevincere.k.base.networking.packages.FuelPackage
import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.postoffices.PostOffice
import co.kodevincere.example.domain.networking.routes.RoutesGet


/**
 * Created by mE on 2/1/18.
 */
class HttpGetPackages {

    companion object {

        fun oneUser(): Package = createPackage(RoutesGet.ROUTE_API, PostOffice.HttpMethod.GET)

        fun multipleUsers(amount: Int): Package {
            val queries = mapOf(Pair("results", "$amount"))

            return createPackage(RoutesGet.ROUTE_API, PostOffice.HttpMethod.GET)
                    .zetQueries(queries)
        }

        private fun createPackage(address: String, method: PostOffice.HttpMethod):  Package{
            return FuelPackage(address)
                    .zetMethod(method)
        }

    }

}