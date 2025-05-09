package com.example.isa.data.datasource.network

import com.example.isa.domain.entity.remote.request.GettingDataRequest
import com.example.isa.domain.entity.remote.request.ModifyingDataRequest
import com.example.isa.domain.entity.remote.response.SelectResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FunctionsApi {
    @POST("DBDataServlet.v.2.0?action=SELECT")
    fun getData(@Body requestBody: GettingDataRequest): Single<SelectResponse>

    @POST("DBDataServlet.v.2.0?action=SIGN_OR_ANN")
    fun changeIsSigned(@Body requestBody: ModifyingDataRequest): Completable

}