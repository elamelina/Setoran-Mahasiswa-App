package com.example.setoranmahasiswa.data

import com.example.setoranmahasiswa.model.Setoran
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("setoran")
    suspend fun getSetoran(): List<Setoran>

    @POST("setoran")
    suspend fun tambahSetoran(@Body setoran: Setoran): Response<Unit>
}