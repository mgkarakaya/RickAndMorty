package com.mgk.melih_rickmorty

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


    //extension function to auto fit items in RecyclerView-gridlayout
    fun RecyclerView.autoFitColumns(columnWidth: Int) {
        val displayMetrics = context.resources.displayMetrics
        val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
        this.layoutManager = GridLayoutManager(context, noOfColumns)
    }
