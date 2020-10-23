package com.construction.app.material.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.material.repository.RepositoryMaterial
import javax.inject.Inject

class ViewModelMaterial @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryMaterial(
                firestore = firestore,
                basePath = basePath()
            )
        }
}