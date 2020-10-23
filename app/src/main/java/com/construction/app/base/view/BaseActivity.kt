package com.construction.app.base.view

import android.content.SharedPreferences
import android.os.Bundle
import com.construction.app.R
import com.construction.app.base.di.factory.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    abstract fun layoutResourceId(): Int
    abstract fun className(): String
    abstract fun pageName(): String

    val TAG = className()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId())
    }

    fun getAppName(): String = getString(R.string.app_name)

    fun getShared() = sharedPreferences

    fun hasSignIn() = firebaseAuth.currentUser != null

    fun getUser() = firebaseAuth.currentUser

    fun signOut() = firebaseAuth.signOut()
}