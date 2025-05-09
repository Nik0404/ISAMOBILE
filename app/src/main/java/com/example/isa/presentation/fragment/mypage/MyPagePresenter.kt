package com.example.isa.presentation.fragment.mypage

import com.arellomobile.mvp.InjectViewState
import com.example.isa.R
import com.example.isa.domain.interactors.mypage.MyPageInteractor
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class MyPagePresenter(private val myPageInteractor: MyPageInteractor) :
    BasePresenter<MyPageView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        getUserData()
    }

    private fun getUserData() {
        subscription.add(
            myPageInteractor.getCurrentUser()
                .subscribe({
                    viewState.setUserData(it)
                }, {
                    viewState.showSnackbar(R.string.user_loading_error_message)
                })
        )
    }

}