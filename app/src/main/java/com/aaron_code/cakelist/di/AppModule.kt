package com.aaron_code.cakelist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.aaron_code.cakelist.interfaces.ICakeService
import com.aaron_code.cakelist.api.CakeApi
import com.aaron_code.cakelist.interfaces.ICakeItemListener
import com.aaron_code.cakelist.interfaces.IDialogService
import com.aaron_code.cakelist.service.CakeService
import com.aaron_code.cakelist.service.DialogService
import com.aaron_code.cakelist.ui.CakeItemListener
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@Module
internal class AppModule {

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/"
    }

    @Provides
    @com.aaron_code.cakelist.di.AppScope
    fun provideViewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
        }
    }

    @Provides
    @com.aaron_code.cakelist.di.AppScope
    fun provideCakeApi(): com.aaron_code.cakelist.api.CakeApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(com.aaron_code.cakelist.di.AppModule.Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val cakeApi = retrofit.create(com.aaron_code.cakelist.api.CakeApi::class.java)
        return cakeApi
    }

    @Provides
    @com.aaron_code.cakelist.di.AppScope
    fun provideCakeService(cakeService: CakeService): ICakeService = cakeService

    @Provides
    @com.aaron_code.cakelist.di.AppScope
    fun provideDialogService(dialogService: DialogService): IDialogService = dialogService

    @Provides
    @com.aaron_code.cakelist.di.AppScope
    fun provideCakeItemListener(cakeItemListener: CakeItemListener): ICakeItemListener = cakeItemListener
}