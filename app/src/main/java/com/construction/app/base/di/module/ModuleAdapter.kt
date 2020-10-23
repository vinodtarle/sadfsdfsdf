package com.construction.app.base.di.module

import com.construction.app.attendance.adapter.AdapterAttendance
import com.construction.app.item.adapter.AdapterItem
import com.construction.app.labour.adapter.AdapterLabour
import com.construction.app.material.adapter.AdapterMaterial
import com.construction.app.payment.adapter.AdapterPayment
import com.construction.app.site.adapter.AdapterSite
import com.construction.app.supplier.adapter.AdapterSupplier
import com.construction.app.unit.adapter.AdapterUnit
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleAdapter {

    @Singleton
    @Provides
    fun adapterAttendance(): AdapterAttendance = AdapterAttendance()

    @Singleton
    @Provides
    fun adapterItem(): AdapterItem = AdapterItem()

    @Singleton
    @Provides
    fun adapterLabour(): AdapterLabour = AdapterLabour()

    @Singleton
    @Provides
    fun adapterMaterial(): AdapterMaterial = AdapterMaterial()

    @Singleton
    @Provides
    fun adapterPayment(): AdapterPayment = AdapterPayment()

    @Singleton
    @Provides
    fun adapterSite(): AdapterSite = AdapterSite()

    @Singleton
    @Provides
    fun adapterSupplier(): AdapterSupplier = AdapterSupplier()

    @Singleton
    @Provides
    fun adapterUnit(): AdapterUnit = AdapterUnit()
}