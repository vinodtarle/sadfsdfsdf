package com.construction.app.report.viewmodel

import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import com.construction.app.report.repository.RepositoryReport
import javax.inject.Inject

class ViewModelReport @Inject internal constructor() : BaseViewModel() {

    override val baseRepository: BaseRepository
        get() {
            return RepositoryReport(
                firestore = firestore,
                basePath = basePath()
            )
        }
}