package com.construction.app.supplier.repository

import com.construction.app.base.constant.Path
import com.construction.app.base.repository.BaseRepository
import com.google.firebase.firestore.FirebaseFirestore

class RepositorySupplier(
    override val firestore: FirebaseFirestore,
    val basePath: String
) : BaseRepository() {
    override val path: String get() = "$basePath/${Path.supplier()}"
}
