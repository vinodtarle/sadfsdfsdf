package com.construction.app.labour.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.construction.app.BR
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.constant.Constant.Companion.BLANK
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Labour(
    @get:Exclude override var id: String = BLANK
) : BaseModule, BaseObservable() {

    @ServerTimestamp
    @get:Bindable
    var createdAt: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdAt)
        }

    @get:Bindable
    var createdBy: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdBy)
        }

    @get:Bindable
    var createdById: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdById)
        }

    @get:Bindable
    var selected: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }

    @get:Bindable
    var status: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }

    @get:Bindable
    var hasData: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.hasData)
        }

    @get:Bindable
    var name: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var contact: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.contact)
        }

    @get:Bindable
    var rate: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.rate)
        }

    @get:Bindable
    var rateType: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.rateType)
        }

    @get:Bindable
    var address: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    override fun toString(): String {
        return name
    }
}