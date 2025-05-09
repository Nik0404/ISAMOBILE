package com.example.isa.presentation.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    protected val subscription = CompositeDisposable()

    override fun onDestroy() {
        subscription.dispose()
        super.onDestroy()
    }
}