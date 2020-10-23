package com.construction.app.site.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.site.repository.RepositorySite
import javax.inject.Inject

class ViewModelSite @Inject internal constructor() : BaseViewModel() {
    private lateinit var repository: RepositorySite

    override val baseRepository: BaseRepository
        get() {
            this.repository = RepositorySite(
                firestore = firestore,
                basePath = basePath()
            )
            return repository
        }
}