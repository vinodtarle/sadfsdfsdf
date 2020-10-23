package com.construction.app.attendance.viewmodel

import com.construction.app.attendance.repository.RepositoryAttendance
import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.utility.basePath
import com.construction.app.base.viewmodel.BaseViewModel
import javax.inject.Inject

class ViewModelAttendance @Inject internal constructor() : BaseViewModel() {
    override val baseRepository: BaseRepository
        get() {
            return RepositoryAttendance(
                firestore = firestore,
                basePath = basePath()
            )
        }
}