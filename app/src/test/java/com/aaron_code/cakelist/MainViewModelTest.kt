package com.aaron_code.cakelist

import com.aaron_code.cakelist.interfaces.ICakeItemListener
import com.aaron_code.cakelist.interfaces.ICakeService
import com.aaron_code.cakelist.interfaces.IDialogService
import com.aaron_code.cakelist.model.Cake
import com.aaron_code.cakelist.ui.MainViewModel
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Suppress("DeferredResultUnused")
@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class MainViewModelTest {

    private val mockCakeService = Mockito.mock(ICakeService::class.java)
    private val mockDialogService = Mockito.mock(IDialogService::class.java)
    private val mockListener = Mockito.mock(ICakeItemListener::class.java)

    private var mainViewModel: MainViewModel? = null

    //TODO: Add more tests to confirm that properties are set correctly for different scenarios

    @Test
    fun testCakesRequestedWhenViewModelCreated() {
        mainViewModel = MainViewModel(mockCakeService, mockDialogService, mockListener)
        Mockito.verify(mockCakeService, times(1)).getCakesAsync()
    }

    @Test
    fun testInProgressTrueWhenCakesRequested() {
        mainViewModel = MainViewModel(mockCakeService, mockDialogService, mockListener)
        Assert.assertTrue(mainViewModel!!.inProgress)
    }

    @Test(expected = Exception::class)
    fun testErrorPresentedWhenErrorDownloadingCakes() {
        Mockito.`when`(mockCakeService.getCakesAsync()).thenThrow(Exception())
        mainViewModel = MainViewModel(mockCakeService, mockDialogService, mockListener)
        Mockito.verify(mockDialogService, times(1)).displayAlert(any(), any())
    }

    @Test
    fun testErrorNotPresentedWhenNoErrorDownloadingCakes() {
        Mockito.`when`(mockCakeService.getCakesAsync()).thenReturn(GlobalScope.async { arrayListOf<Cake>() })
        mainViewModel = MainViewModel(mockCakeService, mockDialogService, mockListener)
        Mockito.verify(mockDialogService, times(0)).displayAlert(any(), any())
    }

    // Provides a kotlin friendly replacement for Mockito.any() that does not fail kotlin non null requirements
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}
