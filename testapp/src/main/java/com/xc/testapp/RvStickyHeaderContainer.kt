package com.xc.testapp

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import timber.log.Timber
import java.text.FieldPosition

/**
 * @author xc
 * @time 18-12-5.
 */
class RvStickyHeaderContainer : FrameLayout {
    private lateinit var headerContainer: FrameLayout
    private lateinit var recyclerView: RecyclerView

    private var headerType: Int = 0
    private var noHeaderPosition: Int = -1

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    fun init(headerType: Int, noHeaderPosition: Int = -1) {
        this.headerType = headerType
        this.noHeaderPosition = noHeaderPosition
        recyclerView = getChildAt(0) as? RecyclerView
                ?: throw RuntimeException("RecyclerView should be the first child view.")

        headerContainer = FrameLayout(context)
        headerContainer.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        addView(headerContainer)

        recyclerView.addOnScrollListener(RvOnScrollListener())
    }

    private inner class RvOnScrollListener : RecyclerView.OnScrollListener() {
        var currentStickyHeaderPosition = -1

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            super.onScrolled(recyclerView, dx, dy)
            val adapter = recyclerView?.adapter
                    ?: throw RuntimeException("Please set adapter")
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                    ?: throw RuntimeException("Only support LinearLayoutManager")

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItemPosition == -1) return
            val relativeStickyHeaderPosition = findRelativeStickyHeaderPosition(firstVisibleItemPosition, adapter)
            if (relativeStickyHeaderPosition == -1) return
            val nextStickyHeaderPosition = findNextStickyHeaderPosition(firstVisibleItemPosition, adapter)

            if (currentStickyHeaderPosition != relativeStickyHeaderPosition) {
                adapter.bindViewHolder(stickyHeaderViewHolder, relativeStickyHeaderPosition)
                currentStickyHeaderPosition = relativeStickyHeaderPosition
            }
            if (relativeStickyHeaderPosition == nextStickyHeaderPosition) return

            val nextStickyHeaderView: View? = layoutManager.findViewByPosition(nextStickyHeaderPosition)

            val realStickHeaderView = stickyHeaderViewHolder.itemView

            val translationY = (nextStickyHeaderView?.top
                    ?: recyclerView.height) - realStickHeaderView.height
            if (translationY > 0) {
                //保持顶部

            } else {
                realStickHeaderView.translationY = translationY.toFloat()
                Timber.e("${realStickHeaderView.top} ${realStickHeaderView.height} ${realStickHeaderView.translationY}")
            }

        }
    }

    private val stickyHeaderViewHolder: RecyclerView.ViewHolder by lazy {
        val viewHolder = recyclerView.adapter.createViewHolder(headerContainer, headerType)
        post {
            headerContainer.addView(viewHolder.itemView)
        }
        viewHolder
    }

    private fun findRelativeStickyHeaderPosition(firstVisibleItemPosition: Int, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): Int {
        for (index in firstVisibleItemPosition downTo 0) {
            val itemViewType = adapter.getItemViewType(index)
            if (itemViewType == headerType) {
                return index
            }
        }
        return -1
    }

    private fun findNextStickyHeaderPosition(firstVisibleItemPosition: Int, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): Int {
        for (index in firstVisibleItemPosition + 1 until adapter.itemCount) {
            val itemViewType = adapter.getItemViewType(index)
            if (itemViewType == headerType) {
                return index
            }
        }
        return -1
    }
}

