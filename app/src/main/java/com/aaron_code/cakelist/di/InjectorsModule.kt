package com.aaron_code.cakelist.di

import com.aaron_code.cakelist.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector(modules = [MainModule.InjectMainViewModel::class])
    abstract fun provideMainActivity(): MainActivity
}