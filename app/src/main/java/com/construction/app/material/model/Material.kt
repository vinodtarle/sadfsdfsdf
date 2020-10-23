package com.construction.app.material.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.construction.app.BR
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.constant.Constant.Companion.BLANK
import com.construction.app.item.model.Item
import com.construction.app.site.model.Site
import com.construction.app.supplier.model.Supplier
import com.construction.app.unit.model.Unit
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Material(
    @get:Exclude
    override var id: String = BLANK
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
    var item: Item? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.item)
        }

    @get:Bindable
    var quantity: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.quantity)
        }

    @get:Bindable
    var unit: Unit? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.unit)
        }

    @get:Bindable
    var rate: String = BLANK
        set(value) {
            field = value
            notifyPropertyChanged(BR.rate)
        }

    @get:Bindable
    var supplier: Supplier? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.supplier)
        }

    @get:Bindable
    var site: Site? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.site)
        }
}