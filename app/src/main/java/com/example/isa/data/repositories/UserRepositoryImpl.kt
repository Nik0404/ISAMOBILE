package com.example.isa.data.repositories

import com.example.isa.data.datasource.database.dao.UserDao
import com.example.isa.data.datasource.network.UserApi
import com.example.isa.domain.entity.local.database.User
import com.example.isa.domain.entity.remote.request.SessionIdRequest
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val preferencesRepository: PreferencesRepository
) : UserRepository {

    override fun loadUserInfo(login: String, sessionId: String): Single<Long> {
        val userId = preferencesRepository.userId
        val requestBody = SessionIdRequest(sessionId)
        return userApi.getUserInfo(requestBody)
            .flatMap {
                val user = User(
                    userId, login, it.userFullNameResponse.name,
                    it.userFullNameResponse.surname,
                    it.userFullNameResponse.thirdName,
                    it.currentSupervisedDivision.divisionName ?: "-",
                    it.email ?: "-",
                    it.post ?: "-",
                    it.phone ?: "-",
//                    it.defaultSupervisedDivision.divisionId
                )
                userDao.saveUser(user)
            }
    }

    override fun getCurrentUser(): Single<User> {
        val userId = preferencesRepository.userId
        return userDao.getUserById(userId)
    }
}
