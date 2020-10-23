package com.construction.app.base

import com.bumptech.glide.Glide
import com.construction.app.base.di.component.AppComponent
import com.construction.app.base.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

open class BaseApplication : DaggerApplication(), MyApp {

    private lateinit var component: AppComponent

    override fun applicationInjector(): AppComponent {
        this.component = DaggerAppComponent
            .builder()
            .create(this) as AppComponent
        return this.component
    }

    override fun getAppComponent(): AppComponent = this.component

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).onTrimMemory(level)
    }
}