package com.construction.app.item.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.construction.app.BR

class UI : BaseObservable() {
    @get:Bindable
    var update: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdBy)
        }
}