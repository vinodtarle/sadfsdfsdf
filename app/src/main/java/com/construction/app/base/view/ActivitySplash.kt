package com.construction.app.base.view

import android.content.Intent
import android.os.Bundle
import com.construction.app.R
import com.construction.app.auth.view.ActivitySignIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivitySplash : BaseActivity() {
    override fun layoutResourceId() = R.layout.activity_splash
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Splash Screen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        finishSplash()
    }

    private fun init() {
        supportActionBar?.hide()
    }

    private fun finishSplash() {
        CoroutineScope(Main).launch {
            delay(3000)
            startActivity(
                Intent(
                    this@ActivitySplash,
                    if (hasSignIn()) ActivityMain::class.java else ActivitySignIn::class.java
                )
            )
            this@ActivitySplash.finish()
        }
    }
}
