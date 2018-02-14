package co.kodevincere.example.ui.main


import co.kodevincere.example.domain.baseextensions.JaSONRequestRealmPresenter
import co.kodevincere.example.domain.baseextensions.writeTransaction
import co.kodevincere.example.domain.models.User
import co.kodevincere.example.domain.networking.packages.HttpGetPackages
import co.kodevincere.example.ui.detail.ActivityDetail
import co.kodevincere.example.ui.detail.FragmentDetail
import co.kodevincere.example.ui.main.recyclerview.ViewHolderMain.Companion.KEY_ACTION_ID_PROFILE_PICTURE
import co.kodevincere.example.ui.main.recyclerview.ViewHolderMain.Companion.KEY_ACTION_ID_ROOT
import co.kodevincere.k.base.networking.packages.Package
import co.kodevincere.k.base.networking.responses.JaSONResponse
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack
import io.reactivex.Observable

/**
 * Created by mE on 2/1/18.
 */
class PresenterMain: JaSONRequestRealmPresenter<User, ViewModelMain>() {

//class PresenterMain: JaSONRequestListPresenter<User, ViewModelMain>() {

    override var viewModel: ViewModelMain? = null

    override fun getObjectClass(): Class<User> = User::class.java

    override fun getElementPackage(): Package = HttpGetPackages.multipleUsers(15)

    override fun handleResponse(response: JaSONResponse): Observable<User> {
        if(!response.success){
            return Observable.just(User.Companion.EMPTY)
        }

        val jason = response.body!!

        val results = jason.getListJaSON("results")

        val usersList = mutableListOf<User>()

        (0 until results.size).mapTo(usersList){
            val user = User(results[it])
            user
        }

        return Observable.fromIterable(usersList)
    }

    override fun onResume() {
        super.onResume()
        makeFullRequest()
    }

    override fun canRequestNext(): Boolean = canMakeRequest() && itemList.size < 90

    override fun requestNext(): Boolean {
        makeFullRequest()
        return true
    }

    override fun handleListAction(goodOldActionPack: GoodOldActionPack<User>) {
        when(goodOldActionPack.actionId){
            KEY_ACTION_ID_PROFILE_PICTURE -> showUserActivity(goodOldActionPack.item)
            KEY_ACTION_ID_ROOT -> {
                writeTransaction {
                    goodOldActionPack.item?.name = "Marica"
                }
                updateItemAtPosition(goodOldActionPack.position)
            }
        }
    }

    private fun showUserActivity(user: User?) {
        val bundle = FragmentDetail.createShowBundle(user?.id, user?.name, user?.lastname, user?.profilePicture)
        showActivityForResult(ActivityDetail::class.java, bundle, 919)
    }

}