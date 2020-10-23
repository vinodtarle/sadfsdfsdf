package com.construction.app.labour.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.labour.repository.RepositoryLabour
import javax.inject.Inject

class ViewModelLabour @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryLabour(
                firestore = firestore,
                basePath = basePath()
            )
        }
}