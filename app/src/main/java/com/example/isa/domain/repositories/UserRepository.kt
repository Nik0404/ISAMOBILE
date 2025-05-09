package com.example.isa.domain.repositories

import com.example.isa.domain.entity.local.database.User
import io.reactivex.Single

interface UserRepository {

    fun loadUserInfo(login: String, sessionId: String): Single<Long>

    fun getCurrentUser(): Single<User>
}
