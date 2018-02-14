package co.kodevincere.k.base.ui.recyclerview

import co.kodevincere.k.base.ui.BaseViewModel
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack

/**
 * Created by mE on 2/12/18.
 */
interface ViewHolderViewModel: BaseViewModel {

    fun dispatchTouch(actionId: Int = GoodOldActionPack.UNDEFINED_INT)

    fun dispatchClick(actionId: Int = GoodOldActionPack.UNDEFINED_INT)

    fun dispatchLongClick(actionId: Int = GoodOldActionPack.UNDEFINED_INT)

}