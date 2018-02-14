package co.kodevincere.k.base.ui.recyclerview

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * Created by mE on 2/1/18.
 */
class ViewHolderLoading<I>(itemView: View?) : BaseViewHolder<I>(itemView, null) {

    override fun clean() {

    }

    override fun bindItem(item: I?) {
        super.bindItem(item)
        val layoutParams = itemView.layoutParams
        when(layoutParams){
            is StaggeredGridLayoutManager.LayoutParams-> {
                layoutParams.isFullSpan = true
            }
        }
    }

}