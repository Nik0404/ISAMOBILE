package com.example.isa.data.datasource.network

import com.example.isa.domain.entity.remote.request.LoginHostRequest
import com.example.isa.domain.entity.remote.request.LogoutRequest
import com.example.isa.domain.entity.remote.request.RegisterFunctionRequest
import com.example.isa.domain.entity.remote.response.LoginResponse
import com.example.isa.domain.entity.remote.response.SessionIdResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionApi {

    @POST("LoginServlet.v.2.0?action=LOGIN")
    fun loginHost(@Body body: LoginHostRequest): Single<LoginResponse>

    @POST("LoginServlet.v.2.0?action=LOGOUT")
    fun logout(@Body body: LogoutRequest): Completable

    @POST("LoginServlet.v.2.0?action=FUNCTION")
    fun registerFunction(@Body body: RegisterFunctionRequest): Single<SessionIdResponse>
}
