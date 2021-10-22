package com.aaron_code.cakelist.service

import com.aaron_code.cakelist.interfaces.ICakeService
import com.aaron_code.cakelist.api.CakeApi
import com.aaron_code.cakelist.model.Cake
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

open class CakeService @Inject constructor(
    private val cakeApi: com.aaron_code.cakelist.api.CakeApi
): ICakeService {

    override fun getCakesAsync(): Deferred<List<Cake>> {
        return GlobalScope.async {
            val cakes = cakeApi.getCakesAsync().await()
            val distinctCakes = cakes.distinctBy { it.title }
            val sortedCakes = distinctCakes.sortedBy { it.title }
            sortedCakes
        }
    }
}