package co.kodevincere.example.ui.main.recyclerview

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.TextView
import co.kodevincere.example.R
import co.kodevincere.example.domain.medias.images.show
import co.kodevincere.example.domain.models.User
import co.kodevincere.k.base.ui.recyclerview.BaseViewHolderWithPresenter
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by mE on 2/2/18.
 */
class ViewHolderMain(itemView: View?, actionListener: ListActionListener<User>?) :
        BaseViewHolderWithPresenter<User, ViewHolderViewModelMain, PresenterViewHolderMain>(itemView, actionListener), ViewHolderViewModelMain{

    private val tvName = view<TextView>(R.id.tv_name)
    private val clRoot = view<ConstraintLayout>(R.id.cl_root)
    private val tvLastname = view<TextView>(R.id.tv_last_name)
    private var ivProfilePicture = view<SimpleDraweeView>(R.id.iv_profile)

    companion object {
        const val KEY_ACTION_ID_ROOT = 1
        const val KEY_ACTION_ID_PROFILE_PICTURE = 2
    }

    override fun bindToPresenter() {
        presenter?.bindViewModel(this)
    }

    override fun startPresenter(): PresenterViewHolderMain {
        return PresenterViewHolderMain()
    }

    override fun clean() {
        tvName?.text = ""
        tvLastname?.text = ""
    }

    override fun bindItem(item: User?) {
        super.bindItem(item)

        tvName?.text = item?.name
        tvLastname?.text = item?.lastname
        ivProfilePicture?.show(item?.profilePicture)
    }

    override fun initListeners() {
        super.initListeners()

        if(clRoot?.hasOnClickListeners() == false){
            clRoot.onClick {
                presenter?.onRootClicked()
            }
        }

        if(ivProfilePicture?.hasOnClickListeners() == false){
            ivProfilePicture?.onClick {
                presenter?.onProfilePictureClicked()
            }
        }

    }

}