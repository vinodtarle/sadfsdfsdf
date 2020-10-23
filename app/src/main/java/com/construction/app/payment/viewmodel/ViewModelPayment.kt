package com.construction.app.payment.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.payment.repository.RepositoryPayment
import javax.inject.Inject

class ViewModelPayment @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryPayment(
                firestore = firestore,
                basePath = basePath()
            )
        }
}