package com.aaron_code.cakelist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aaron_code.cakelist.ui.MainActivity
import com.aaron_code.cakelist.ui.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainModule.ProvideMainViewModel::class, InjectorsModule::class])
abstract class MainModule {

    @Module
    class ProvideMainViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel = mainViewModel
    }

    @Module
    class InjectMainViewModel {

        @Provides
        fun provideMainViewModelProvider(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ) = ViewModelProviders.of(target, factory).get(MainViewModel::class.java)
    }
}