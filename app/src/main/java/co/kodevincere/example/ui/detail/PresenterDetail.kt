package co.kodevincere.example.ui.detail

import android.app.Activity
import android.os.Bundle
import co.kodevincere.example.domain.baseextensions.realm
import co.kodevincere.example.domain.baseextensions.writeTransaction
import co.kodevincere.example.domain.models.User
import co.kodevincere.example.ui.detail.changename.FragmentChangeName
import co.kodevincere.example.ui.main.MainActivity
import co.kodevincere.k.base.presenter.BaseScreenPresenter
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by mE on 2/8/18.
 */
class PresenterDetail: BaseScreenPresenter<ViewModelDetail>() {

    override var viewModel: ViewModelDetail? = null

    private var id: String = ""
    private var name: String = ""
    private var lastName: String = ""
    private var pictureUrl: String = ""

    var textFieldDisposable: Subject<String> = PublishSubject.create()

    override fun processBundle(bundle: Bundle) {
        super.processBundle(bundle)
        val action = bundle.getInt(FragmentDetail.KEY_ACTION)

        if(action == FragmentDetail.VALUE_ACTION_SHOW){
            processShowBundle(bundle)
        }else if(action == FragmentDetail.VALUE_ACTION_UPDATE){
            processUpdateBundle(bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel?.showName(name)
        viewModel?.showLastName(lastName)
        viewModel?.showPicture(pictureUrl)
    }

    override fun messageDismissed(messageCode: Int) {
        super.messageDismissed(messageCode)
        if(messageCode == 939){
            val intent = MainActivity.createDetailResultIntent("Puta madre")
            viewModel?.returnResultAndFinish(Activity.RESULT_OK, intent)
        }
    }

    override fun initViewDisposables() {
        super.initViewDisposables()

        val disposable = textFieldDisposable
                .map {
                    text->
                    text.isNotEmpty()
                }
                .subscribe{
                    viewModel?.testButtonEnabled?.onNext(it)
                }

        addToViewDisposables(disposable)
    }

    private fun processShowBundle(bundle: Bundle) {
        id = bundle.getString(FragmentDetail.KEY_ID)
        name = bundle.getString(FragmentDetail.KEY_NAME)
        lastName = bundle.getString(FragmentDetail.KEY_LAST_NAME)
        pictureUrl = bundle.getString(FragmentDetail.KEY_PICTURE_URL)
    }

    private fun processUpdateBundle(bundle: Bundle) {
        id = bundle.getString(FragmentDetail.KEY_ID)
        name = bundle.getString(FragmentDetail.KEY_NAME)
        val user = realm.where(User::class.java).equalTo("id", id)
                .findFirst()
        user?.let {
            writeTransaction {
                user.name = name
            }
        }
    }

    fun testingClicked(text: String?) {
        viewModel?.showMessageAndNotifyOnDismiss(text ?: "Nothing", 939)
    }

    fun changeClicked() {
        val bundle = FragmentChangeName.createBundle(id)
        showFragment(FragmentChangeName.newInstance(bundle), true)
    }

}