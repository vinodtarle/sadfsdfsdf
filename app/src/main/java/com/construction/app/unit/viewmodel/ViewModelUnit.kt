package com.construction.app.unit.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.unit.repository.RepositoryUnit
import javax.inject.Inject

class ViewModelUnit @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryUnit(
                firestore = firestore,
                basePath = basePath()
            )
        }
}