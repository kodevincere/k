package co.kodevincere.k.base.ui.recyclerview.actions

/**
 * Created by mE on 2/7/18.
 */
class GoodOldActionPack<I> {

    companion object {
        @JvmStatic
        val UNDEFINED_INT = -1
    }

    var item: I? = null
    var actionId = UNDEFINED_INT
    var position = UNDEFINED_INT
    var viewType = UNDEFINED_INT
    var action = GoodOldAction.NONE

    fun zetItem(item: I?): GoodOldActionPack<I>{
        this.item = item
        return this
    }

    fun zetActionId(actionId: Int): GoodOldActionPack<I>{
        this.actionId = actionId
        return this
    }

    fun zetPosition(position: Int): GoodOldActionPack<I>{
        this.position = position
        return this
    }

    fun zetViewType(viewType: Int): GoodOldActionPack<I>{
        this.viewType = viewType
        return this
    }

    fun zetAction(action: GoodOldAction): GoodOldActionPack<I>{
        this.action = action
        return this
    }

    enum class GoodOldAction{
        NONE,
        TOUCH,
        CLICK,
        LONG_CLICK,
    }

}