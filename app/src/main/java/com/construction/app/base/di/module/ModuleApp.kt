package com.construction.app.base.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.construction.app.R
import com.construction.app.base.BaseApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleApp {

    @Singleton
    @Provides
    fun applicationContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun firebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun firestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun requestOption(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher_round)
    }

    @Singleton
    @Provides
    fun requestManager(application: Application, option: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(option)
    }

    @Singleton
    @Provides
    fun drawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.mipmap.ic_launcher)
    }
}