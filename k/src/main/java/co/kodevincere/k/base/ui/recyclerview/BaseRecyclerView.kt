package co.kodevincere.k.base.ui.recyclerview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.ViewGroup
import co.kodevincere.k.R
import co.kodevincere.k.base.BaseApp

/**
 * Created by mE on 2/5/18.
 */
class BaseRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), SwipeRefreshLayout.OnRefreshListener{

    companion object {
        private val primaryColor = ContextCompat.getColor(BaseApp.sInstance, R.color.colorPrimary)
    }

    private var processed = false
    protected var itemsThreshold = 6
    private var swipeToRefresh: Boolean = false
    protected var srl: SwipeRefreshLayout? = null
    protected var swipeToRefreshColor1: Int = primaryColor
    protected var swipeToRefreshColor2: Int = primaryColor
    protected var swipeToRefreshColor3: Int = primaryColor


    var actionListener: BaseRecyclerViewActionListener? = null

    init {
        extractAttr(attrs)
    }

    private fun extractAttr(attrs: AttributeSet?) {
        if(attrs == null){
            return
        }

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BaseRecyclerView
                , 0, 0)

        try {
            val swipeToRefresh = typedArray.getBoolean(R.styleable.BaseRecyclerView_swipeToRefresh,
                    false)
            val swipeToRefreshColor1 = typedArray.getColor(R.styleable.BaseRecyclerView_swipeToRefreshColor1,
                    primaryColor)
            val swipeToRefreshColor2 = typedArray.getColor(R.styleable.BaseRecyclerView_swipeToRefreshColor2,
                    primaryColor)
            val swipeToRefreshColor3 = typedArray.getColor(R.styleable.BaseRecyclerView_swipeToRefreshColor3,
                    primaryColor)

            val itemsThreshold = typedArray.getInt(R.styleable.BaseRecyclerView_itemsThreshold, 6)

            this.swipeToRefresh = swipeToRefresh
            this.swipeToRefreshColor1 = swipeToRefreshColor1
            this.swipeToRefreshColor2 = swipeToRefreshColor2
            this.swipeToRefreshColor3 = swipeToRefreshColor3

            this.itemsThreshold = itemsThreshold

        }finally {
            typedArray.recycle()
        }

    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        this.addOnScrollListener(ScrollListener(layout))
    }

    override fun onRefresh() {
        if(actionListener == null){
            stopRefreshing()
            return
        }
        actionListener?.onRefresh()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if(swipeToRefresh){
            addSwipeToRefresh()
        }
    }

    fun startRefreshing(){
        srl?.isRefreshing = true
    }

    fun stopRefreshing(){
        srl?.isRefreshing = false
    }

    private fun addSwipeToRefresh() {

        if(processed) return

        processed = true

        srl = SwipeRefreshLayout(context)
        val layoutParams = this.layoutParams
        val parentViewGroup = parent as ViewGroup
        val viewIndex = parentViewGroup.indexOfChild(this)

        parentViewGroup.removeView(this)
        srl?.setColorSchemeColors(swipeToRefreshColor1, swipeToRefreshColor2, swipeToRefreshColor3)
        srl?.addView(this)
        srl?.setOnRefreshListener(this)
        parentViewGroup.addView(srl!!, viewIndex, layoutParams)
        parentViewGroup.invalidate()
    }

    interface BaseRecyclerViewActionListener {
        fun onRefresh()
        fun canRequestNext(): Boolean
        fun requestNext(): Boolean
    }

    inner class ScrollListener(val layoutManager: LayoutManager?) : OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if(actionListener == null){
                return
            }

            val lastVisiblePosition = getLastVisiblePosition()

            if(lastVisiblePosition == RecyclerView.NO_POSITION){
                return
            }

            checkScroll(lastVisiblePosition)

        }

        private fun getLastVisiblePosition(): Int = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is StaggeredGridLayoutManager -> layoutManager.findLastVisibleItemPositions(null).max() ?: 0
            else -> throw Error("Unexpected Layout Manager: $layoutManager")
        }

    }

    private fun checkScroll(lastVisiblePosition: Int) {
        val itemCount = adapter.itemCount

        val canShowLoader = lastVisiblePosition >= itemCount - itemsThreshold

        if(canShowLoader){
            checkScrollRequest()
        }
    }

    private fun checkScrollRequest(){
        if(actionListener?.canRequestNext() == true){
            val askedForNext = actionListener?.requestNext() ?: false
            if(askedForNext){
                post({
                    (adapter as? BaseRecyclerAdapter<*, *>)?.insertLoading()
                })
            }
        }
    }

}