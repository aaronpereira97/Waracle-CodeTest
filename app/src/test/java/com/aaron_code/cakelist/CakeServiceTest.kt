package com.aaron_code.cakelist

import com.aaron_code.cakelist.api.CakeApi
import com.aaron_code.cakelist.model.Cake
import com.aaron_code.cakelist.service.CakeService

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CakeServiceTest {

    private val mockCakeApi = Mockito.mock(com.aaron_code.cakelist.api.CakeApi::class.java)

    private var cakeService: CakeService? = null

    @Before
    fun setup() {
        cakeService = CakeService(mockCakeApi)
        Mockito.`when`(mockCakeApi.getCakesAsync()).thenReturn(GlobalScope.async { createUnorderedCakeList() })
    }

    @Test
    fun testResultsAreFiltered() {
        runBlocking {
            val results = cakeService!!.getCakesAsync().await()
            Assert.assertEquals(5, results.count())
        }
    }

    @Test
    fun testResultsAreOrdered() {
        runBlocking {
            val results = cakeService!!.getCakesAsync().await()
            Assert.assertEquals("A", results[0].title)
            Assert.assertEquals("B", results[1].title)
            Assert.assertEquals("R", results[2].title)
            Assert.assertEquals("Y", results[3].title)
            Assert.assertEquals("Z", results[4].title)
        }
    }

    private fun createUnorderedCakeList(): List<Cake> {

        val cake1 = Cake("A", "", "")
        val cake2 = Cake("Z", "", "")
        val cake3 = Cake("R", "", "")
        val cake4 = Cake("A", "", "")
        val cake5 = Cake("Y", "", "")
        val cake6 = Cake("Z", "", "")
        val cake7 = Cake("B", "", "")

        return arrayListOf(cake1, cake2, cake3, cake4, cake5, cake6, cake7)
    }
}