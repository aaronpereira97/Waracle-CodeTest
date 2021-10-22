package com.aaron_code.cakelist.api

import com.aaron_code.cakelist.model.Cake
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CakeApi {

    @GET("waracle_cake-android-client")
    fun getCakesAsync(): Deferred<List<Cake>>
}