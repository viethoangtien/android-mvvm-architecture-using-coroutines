package com.soict.hoangviet.procoroutines.custom.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsDecoration(val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        when (position) {
            0 -> {
                outRect.apply {
                    top = padding
                    bottom = padding
                    left = padding
                    right = padding
                }
            }
            else -> {
                outRect.apply {
                    bottom = padding
                    left = padding
                    right = padding
                }
            }
        }
    }
}