package com.example.isa.presentation.mvp

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ccom.example.isa.presentation.mvp.BaseView

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    fun <T : ViewDataBinding> getDataBindengView(@LayoutRes layoutId: Int): T =
        DataBindingUtil.setContentView(this, layoutId)

    override fun showToast(resourcesId: Int) {
        Toast.makeText(this, resourcesId, Toast.LENGTH_LONG).show()
    }

    override fun showToast(resourcesId: Int, text: String) {
        Toast.makeText(this, getString(resourcesId, text), Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}