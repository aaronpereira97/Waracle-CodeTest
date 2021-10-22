package com.aaron_code.cakelist.interfaces

import com.aaron_code.cakelist.model.Cake
import kotlinx.coroutines.Deferred

interface ICakeService {
    fun getCakesAsync(): Deferred<List<Cake>>
}