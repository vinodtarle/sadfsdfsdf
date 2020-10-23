package com.construction.app.base

import com.construction.app.base.di.component.AppComponent


interface MyApp {
    fun getAppComponent(): AppComponent
}