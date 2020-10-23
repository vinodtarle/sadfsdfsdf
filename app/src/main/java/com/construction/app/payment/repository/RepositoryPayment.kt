package com.construction.app.payment.repository

import com.construction.app.base.constant.Path
import com.construction.app.base.repository.BaseRepository
import com.google.firebase.firestore.FirebaseFirestore

class RepositoryPayment(
    override val firestore: FirebaseFirestore,
    val basePath: String
) : BaseRepository() {
    override val path: String get() = "$basePath/${Path.payment()}"
}
