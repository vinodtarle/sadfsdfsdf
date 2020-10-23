package com.construction.app.base.di.module

import com.construction.app.auth.view.ActivitySignIn
import com.construction.app.base.view.ActivityMain
import com.construction.app.base.view.ActivitySplash
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ModuleActivity {

    @ContributesAndroidInjector
    internal abstract fun activitySplash(): ActivitySplash

    @ContributesAndroidInjector
    internal abstract fun activitySignIn(): ActivitySignIn

    @ContributesAndroidInjector
    internal abstract fun activityMain(): ActivityMain
}