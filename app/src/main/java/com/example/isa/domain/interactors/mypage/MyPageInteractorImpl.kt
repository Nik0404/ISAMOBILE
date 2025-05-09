package com.example.isa.domain.interactors.mypage

import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.domain.entity.local.database.User
import com.example.isa.domain.repositories.UserRepository
import io.reactivex.Single

class MyPageInteractorImpl(
    private val userRepository: UserRepository,
    private val schedulersProvider: SchedulersProvider
) : MyPageInteractor {

    override fun getCurrentUser(): Single<User> {
        return userRepository.getCurrentUser()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }
}