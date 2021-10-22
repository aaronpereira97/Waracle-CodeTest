package com.aaron_code.cakelist.di

import com.aaron_code.cakelist.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, com.aaron_code.cakelist.di.InjectorsModule::class, com.aaron_code.cakelist.di.AppModule::class, com.aaron_code.cakelist.di.MainModule::class])
@com.aaron_code.cakelist.di.AppScope
interface AppComponent: AndroidInjector<com.aaron_code.cakelist.MyApplication>