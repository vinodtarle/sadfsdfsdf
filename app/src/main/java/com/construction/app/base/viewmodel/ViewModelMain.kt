package com.construction.app.base.viewmodel

import com.construction.app.base.repository.BaseRepository
import javax.inject.Inject

class ViewModelMain @Inject internal constructor() : BaseViewModel() {

    private lateinit var repository: BaseRepository

    override val baseRepository: BaseRepository
        get() {
            // Don't forget to initialized the repository
            return repository
        }
}