package com.example.gonzaloaliaga.data.api

import com.example.gonzaloaliaga.model.Usuario
import com.example.gonzaloaliaga.model.UsuarioResponse
import retrofit2.http.*

interface UserApi {

    @GET("api/users")
    suspend fun getAllUsers(): UsuarioResponse

    @GET("api/users/{id}")
    suspend fun getUserById(@Path("id") id: String): Usuario

    @GET("api/users/login")
    suspend fun login(
        @Query("correo") correo: String,
        @Query("pass") pass: String,
    ) : Usuario

    @POST("api/users")
    suspend fun createUser(@Body usuario: Usuario): Usuario

    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body user: Usuario
    ) : Usuario

    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String)
}