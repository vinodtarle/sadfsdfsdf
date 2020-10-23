package com.construction.app.base.utility

import com.construction.app.base.viewmodel.BaseViewModel

fun BaseViewModel.basePath(): String {
    return "${sharedPreferences.getString(
        "appName", "app_name"
    )}/${firebaseAuth.uid}"
}