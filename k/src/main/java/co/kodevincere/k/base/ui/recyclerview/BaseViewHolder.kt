package co.kodevincere.k.base.ui.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack
import co.kodevincere.k.base.ui.recyclerview.actions.GoodOldActionPack.GoodOldAction
import co.kodevincere.k.base.ui.recyclerview.actions.ListActionListener

/**
 * Created by mE on 2/1/18.
 */
abstract class BaseViewHolder<I>(itemView: View?, val actionListener: ListActionListener<I>?) :
        RecyclerView.ViewHolder(itemView), ViewHolderViewModel {

    protected var item: I? = null
    protected var viewContext: Context = itemView!!.context

    override fun viewContext(): Context = viewContext

    fun <V: View> view(id: Int): V? = itemView.findViewById(id)

    abstract fun clean()

    //To set click listeners and more
    open protected fun initListeners() {

    }

    open fun bindItem(item: I?){
        this.item = item
        initListeners()
        this.clean()
    }

    override fun dispatchTouch(actionId: Int){
        dispatchAction(actionId, GoodOldAction.TOUCH)
    }

    override fun dispatchClick(actionId: Int){
        dispatchAction(actionId, GoodOldAction.CLICK)
    }

    override fun dispatchLongClick(actionId: Int){
        dispatchAction(actionId, GoodOldAction.LONG_CLICK)
    }

    private fun dispatchAction(viewId: Int, action: GoodOldAction){
        if(actionListener == null){
            return
        }

        val goodOldActionPack =
                GoodOldActionPack<I>()
                        .zetItem(item)
                        .zetAction(action)
                        .zetActionId(viewId)
                        .zetViewType(itemViewType)
                        .zetPosition(adapterPosition)

        actionListener.handleListAction(goodOldActionPack)

    }

}