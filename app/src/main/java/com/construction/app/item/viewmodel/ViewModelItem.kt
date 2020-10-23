package com.construction.app.item.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.item.repository.RepositoryItem
import javax.inject.Inject

class ViewModelItem @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryItem(
                firestore = firestore,
                basePath = basePath()
            )
        }
}