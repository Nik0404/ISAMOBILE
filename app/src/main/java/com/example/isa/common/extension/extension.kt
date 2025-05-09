package com.example.isa.common.extension

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

inline fun DrawerLayout.addQrDrawerListener(crossinline onDrawerClosed: (DrawerLayout.DrawerListener) -> Unit) {
    this.addDrawerListener(object : DrawerLayout.DrawerListener {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) = Unit
        override fun onDrawerOpened(drawerView: View) = Unit
        override fun onDrawerClosed(drawerView: View) = Unit
        override fun onDrawerStateChanged(newState: Int) = onDrawerClosed(this)

    })
}
