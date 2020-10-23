package com.construction.app.attendance.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.construction.app.BR
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.constant.Constant.Companion.BLANK
import com.construction.app.labour.model.Labour
import com.construction.app.site.model.Site
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Attendance(
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
    var date: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }

    @get:Bindable
    var labour: Labour? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.labour)
        }

    @get:Bindable
    var site: Site? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.site)
        }

    @get:Bindable
    var dayType: String = "Full Day"
        set(value) {
            field = value
            notifyPropertyChanged(BR.dayType)
        }
}