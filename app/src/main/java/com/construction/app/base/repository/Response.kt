package com.construction.app.base.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class Response(
    val loading: Boolean = false,
    val status: Boolean = false,
    val document: DocumentSnapshot? = null,
    val exception: Exception? = null,
    val collection: QuerySnapshot? = null,
    val firestoreException: FirebaseFirestoreException? = null
)