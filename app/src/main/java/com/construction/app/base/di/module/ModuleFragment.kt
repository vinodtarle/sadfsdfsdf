package com.construction.app.base.di.module

import com.construction.app.attendance.view.FragmentAttendance
import com.construction.app.attendance.view.FragmentAttendanceAdd
import com.construction.app.base.view.FragmentMain
import com.construction.app.item.view.FragmentItem
import com.construction.app.item.view.FragmentItemAdd
import com.construction.app.labour.view.FragmentLabour
import com.construction.app.labour.view.FragmentLabourAdd
import com.construction.app.labour.view.FragmentLabourDetails
import com.construction.app.material.view.FragmentMaterial
import com.construction.app.material.view.FragmentMaterialAdd
import com.construction.app.material.view.FragmentMaterialDetails
import com.construction.app.payment.view.FragmentPayment
import com.construction.app.payment.view.FragmentPaymentAdd
import com.construction.app.payment.view.FragmentPaymentDetails
import com.construction.app.report.view.FragmentDetails
import com.construction.app.site.view.FragmentSite
import com.construction.app.site.view.FragmentSiteAdd
import com.construction.app.site.view.FragmentSiteDetails
import com.construction.app.supplier.view.FragmentSupplier
import com.construction.app.supplier.view.FragmentSupplierAdd
import com.construction.app.supplier.view.FragmentSupplierDetails
import com.construction.app.unit.view.FragmentUnit
import com.construction.app.unit.view.FragmentUnitAdd
import com.construction.app.unit.view.FragmentUnitDetails
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ModuleFragment {

    @ContributesAndroidInjector
    internal abstract fun fragmentMain(): FragmentMain

    @ContributesAndroidInjector
    internal abstract fun fragmentAttendance(): FragmentAttendance

    @ContributesAndroidInjector
    internal abstract fun fragmentAttendanceAdd(): FragmentAttendanceAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentItem(): FragmentItem

    @ContributesAndroidInjector
    internal abstract fun fragmentItemAdd(): FragmentItemAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentLabour(): FragmentLabour

    @ContributesAndroidInjector
    internal abstract fun fragmentLabourAdd(): FragmentLabourAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentLabourDetails(): FragmentLabourDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentMaterial(): FragmentMaterial

    @ContributesAndroidInjector
    internal abstract fun fragmentMaterialAdd(): FragmentMaterialAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentMaterialDetails(): FragmentMaterialDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentPayment(): FragmentPayment

    @ContributesAndroidInjector
    internal abstract fun fragmentPaymentAdd(): FragmentPaymentAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentPaymentDetails(): FragmentPaymentDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentSite(): FragmentSite

    @ContributesAndroidInjector
    internal abstract fun fragmentSiteAdd(): FragmentSiteAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentSiteDetails(): FragmentSiteDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentSupplier(): FragmentSupplier

    @ContributesAndroidInjector
    internal abstract fun fragmentSupplierAdd(): FragmentSupplierAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentSupplierDetails(): FragmentSupplierDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentUnit(): FragmentUnit

    @ContributesAndroidInjector
    internal abstract fun fragmentUnitAdd(): FragmentUnitAdd

    @ContributesAndroidInjector
    internal abstract fun fragmentUnitDetails(): FragmentUnitDetails

    @ContributesAndroidInjector
    internal abstract fun fragmentDetails(): FragmentDetails
}