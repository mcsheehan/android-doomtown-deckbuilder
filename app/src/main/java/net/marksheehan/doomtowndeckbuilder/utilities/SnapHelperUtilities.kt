package net.marksheehan.doomtowndeckbuilder.utilities

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = this.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}