package com.construction.app.base.di.module

import androidx.lifecycle.ViewModel
import com.construction.app.attendance.viewmodel.ViewModelAttendance
import com.construction.app.base.di.factory.ViewModelKey
import com.construction.app.base.viewmodel.ViewModelMain
import com.construction.app.item.viewmodel.ViewModelItem
import com.construction.app.labour.viewmodel.ViewModelLabour
import com.construction.app.material.viewmodel.ViewModelMaterial
import com.construction.app.payment.viewmodel.ViewModelPayment
import com.construction.app.report.viewmodel.ViewModelReport
import com.construction.app.site.viewmodel.ViewModelSite
import com.construction.app.supplier.viewmodel.ViewModelSupplier
import com.construction.app.unit.viewmodel.ViewModelUnit
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ModuleViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMain::class)
    internal abstract fun viewModelMain(model: ViewModelMain): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelAttendance::class)
    internal abstract fun viewModelAttendance(model: ViewModelAttendance): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelItem::class)
    internal abstract fun viewModelItem(model: ViewModelItem): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLabour::class)
    internal abstract fun viewModelLabour(model: ViewModelLabour): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMaterial::class)
    internal abstract fun viewModelMaterial(model: ViewModelMaterial): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelPayment::class)
    internal abstract fun viewModelPayment(model: ViewModelPayment): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelReport::class)
    internal abstract fun viewModelReport(model: ViewModelReport): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSite::class)
    internal abstract fun viewModelSite(model: ViewModelSite): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSupplier::class)
    internal abstract fun viewModelSupplier(model: ViewModelSupplier): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelUnit::class)
    internal abstract fun viewModelUnit(model: ViewModelUnit): ViewModel
}