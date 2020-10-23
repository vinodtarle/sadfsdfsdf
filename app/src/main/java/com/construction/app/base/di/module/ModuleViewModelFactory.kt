package com.construction.app.base.di.module

import androidx.lifecycle.ViewModelProvider
import com.construction.app.base.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ModuleViewModelFactory {
    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}