package com.example.isa.data.repositories

import com.example.isa.common.constants.FunctionsConstants
import com.example.isa.data.datasource.database.dao.FunctionDao
import com.example.isa.data.datasource.network.SessionApi
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.UserFunction
import com.example.isa.domain.entity.remote.request.LoginHostRequest
import com.example.isa.domain.entity.remote.request.LogoutRequest
import com.example.isa.domain.entity.remote.request.RegisterFunctionRequest
import com.example.isa.domain.entity.remote.schema.FunctionSchema
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import io.reactivex.Completable
import io.reactivex.Single

class SessionRepositoryImpl(
    private val sessionApi: SessionApi,
    private val userFunctionDao: FunctionDao,
    private val preferencesRepository: PreferencesRepository,
    private val userFunctionMapper: EntityMapper<FunctionSchema, UserFunction>,
) : SessionRepository {

    private val functionCodeList = mutableSetOf<Int>()

    override fun loginUser(
        login: String, password: String, appCode: String, appVersion: String
    ): Single<String> {
        val loginRequest = LoginHostRequest(login, password, appCode, appVersion)

        return sessionApi.loginHost(loginRequest).doOnSuccess {
            preferencesRepository.authSessionId = it.sessionId
            preferencesRepository.login = login
            preferencesRepository.password = password
        }.flatMap { response ->
            val functions = response.functions.map { function ->
                functionCodeList.add(function.functionCode)
                userFunctionMapper.fromSchemaToEntity(function)
            }
            userFunctionDao.saveFunctions(functions)
        }.flatMap {
            Single.just(preferencesRepository.authSessionId)
        }
    }

    override fun logout(logoutRequest: LogoutRequest): Completable {
        return sessionApi.logout(logoutRequest)
    }

    override fun registerFunction(
        sessionId: String, functionId: Int, appCode: String, appVersion: String
    ): Single<String> {
        val requestBody = RegisterFunctionRequest(sessionId, functionId, appCode, appVersion)

        return sessionApi.registerFunction(requestBody)
            .doOnSuccess { response ->
                updateFunctionSessionIds(response.sessionId)
            }
            .map { it.sessionId }
            .doOnError { preferencesRepository.clear() }
    }

    private fun updateFunctionSessionIds(sessionId: String) {
        functionCodeList.forEach { id ->
            when (id) {
                FunctionsConstants.PACKAGE_RECONCILIATION -> {
                    preferencesRepository.functionSessionId = sessionId
                }
            }
        }
    }

    override fun getFunctions(): Single<List<UserFunction>> {
        return userFunctionDao.getFunctions()
    }

    override fun getAppVersionName(): String = preferencesRepository.versionName

    override fun getLogin(): String = preferencesRepository.login

    override fun getPassword(): String = preferencesRepository.password

    override fun getAuthSessionId(): String = preferencesRepository.authSessionId

    override fun getFunctionId(): Int = preferencesRepository.functionPacketNegotiationId

//    private fun loadDataFunction(appVersion: String): Single<String> {
//        var functionId = FunctionsConstants.INVALID_FUNCTION_ID
//        val sessionId = preferencesRepository.authSessionId
//        var appCodeFun = ""
//
//        functionCodeList.forEach { list ->
//            when (list) {
//                FunctionsConstants.PACKAGE_RECONCILIATION -> {
//                    functionId = FunctionsConstants.PACKAGE_RECONCILIATION
//                    appCodeFun = NetworkConstants.APP_PACKET_NEGOTIATION_CODE
//                }
//            }
//        }
//
//        return registerFunction(sessionId, functionId, appCodeFun, appVersion)
//    }

}
