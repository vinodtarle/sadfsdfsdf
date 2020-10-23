package com.construction.app.supplier.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.supplier.repository.RepositorySupplier
import javax.inject.Inject

class ViewModelSupplier @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositorySupplier(
                firestore = firestore,
                basePath = basePath()
            )
        }
}