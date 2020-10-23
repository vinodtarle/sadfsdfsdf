package com.construction.app.base.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.construction.app.base.repository.BaseRepository
import com.construction.app.base.repository.Resource
import com.construction.app.base.repository.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var firestore: FirebaseFirestore

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    internal abstract val baseRepository: BaseRepository

    val update: MutableLiveData<Boolean> = MutableLiveData()

    fun set(data: Any): LiveData<Resource<Response>> {
        return this.baseRepository.set(data = data)
    }

    fun update(documentId: String, key: String, value: Any): LiveData<Resource<Response>> {
        return this.baseRepository.update(
            documentId = documentId,
            key = key,
            value = value
        )
    }

    fun delete(documentId: String): LiveData<Resource<Response>> {
        return this.baseRepository.delete(documentId = documentId)
    }

    fun document(documentId: String): LiveData<Resource<Response>> {
        return this.baseRepository.document(documentId = documentId)
    }

    fun documentListener(documentId: String): LiveData<Resource<Response>> {
        return this.baseRepository.documentListener(documentId = documentId)
    }

    fun collection(): LiveData<Resource<Response>> {
        return this.baseRepository.collection()
    }

    fun collectionListener(): LiveData<Resource<Response>> {
        return this.baseRepository.collectionListener()
    }
}