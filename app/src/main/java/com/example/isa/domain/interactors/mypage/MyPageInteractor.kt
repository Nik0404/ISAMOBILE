package com.example.isa.domain.interactors.mypage

import com.example.isa.domain.entity.local.database.User
import io.reactivex.Single

interface MyPageInteractor {

    fun getCurrentUser(): Single<User>
}