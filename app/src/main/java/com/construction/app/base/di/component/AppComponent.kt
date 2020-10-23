package com.construction.app.base.di.component

import com.construction.app.base.BaseApplication
import com.construction.app.base.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ModuleActivity::class,
        ModuleFragment::class,
        ModuleViewModelFactory::class,
        ModuleViewModel::class,
        ModuleAdapter::class,
        ModuleApp::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication?> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()
}