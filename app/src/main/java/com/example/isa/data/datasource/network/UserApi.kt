package com.example.isa.data.datasource.network

import com.example.isa.domain.entity.remote.request.SessionIdRequest
import com.example.isa.domain.entity.remote.response.UserInfoResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("InfoServlet.v.2.0?action=GET_USER")
    fun getUserInfo(@Body body: SessionIdRequest): Single<UserInfoResponse>
}
