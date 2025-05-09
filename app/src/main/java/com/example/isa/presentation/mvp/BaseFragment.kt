package com.example.isa.presentation.mvp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ccom.example.isa.presentation.mvp.BaseView
import com.example.isa.presentation.custom.listener.IOnBackPressed

abstract class BaseFragment : MvpAppCompatFragment(), BaseView, IOnBackPressed {

    fun <T : ViewDataBinding> getDataBindingView(
        @LayoutRes layoutId: Int,
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T {
        return DataBindingUtil.inflate(inflater, layoutId, container, false)
    }

    override fun showToast(resourcesId: Int) {
        Toast.makeText(context, resourcesId, Toast.LENGTH_LONG).show()
    }

    override fun showToast(resourcesId: Int, text: String) {
        Toast.makeText(context, getString(resourcesId, text), Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}