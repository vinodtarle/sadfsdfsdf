package com.construction.app.report.repository

import com.construction.app.base.constant.Path
import com.construction.app.base.repository.BaseRepository
import com.google.firebase.firestore.FirebaseFirestore

class RepositoryReport(
    override val firestore: FirebaseFirestore,
    val basePath: String
) : BaseRepository() {
    override val path: String get() = "$basePath/${Path.item()}"
}
