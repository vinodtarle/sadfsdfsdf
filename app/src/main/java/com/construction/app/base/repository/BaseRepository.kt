package com.construction.app.base.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseRepository {
    abstract val firestore: FirebaseFirestore
    abstract val path: String

    private val set: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val update: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val delete: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val document: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val documentListener: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val collection: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val collectionListener: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val byStandard: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val byDivision: MutableLiveData<Resource<Response>> = MutableLiveData()
    private val byStudent: MutableLiveData<Resource<Response>> = MutableLiveData()

    fun set(data: Any): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .document()
            .set(data)
            .addOnSuccessListener {
                this.set.value = Resource.Success(
                    Response(status = true)
                )
            }
            .addOnFailureListener { exception ->
                this.set.value = Resource.Error(
                    Response(exception = exception)
                )
            }
        return this.set
    }

    fun update(
        documentId: String,
        key: String,
        value: Any
    ): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .document(documentId)
            .update(key, value)
            .addOnSuccessListener {
                this.update.value = Resource.Success(
                    Response(status = true)
                )
            }
            .addOnFailureListener { exception ->
                this.update.value = Resource.Error(
                    Response(exception = exception)
                )
            }
        return this.update
    }

    fun delete(documentId: String): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .document(documentId)
            .delete()
            .addOnSuccessListener {
                this.delete.value = Resource.Success(
                    Response(status = true)
                )
            }
            .addOnFailureListener { exception ->
                this.delete.value = Resource.Error(
                    Response(exception = exception)
                )
            }
        return this.delete
    }

    fun document(documentId: String): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .document(documentId)
            .get()
            .addOnSuccessListener { document ->
                this.document.value = Resource.Success(
                    Response(document = document, status = true)
                )
            }
            .addOnFailureListener { exception ->
                this.document.value = Resource.Error(
                    Response(exception = exception)
                )
            }
        return this.document
    }

    fun documentListener(documentId: String): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .document(documentId)
            .addSnapshotListener(EventListener { document, exception ->
                if (exception != null) {
                    this.documentListener.value = Resource.Error(
                        Response(firestoreException = exception)
                    )
                    return@EventListener
                }
                this.documentListener.value = Resource.Success(
                    Response(document = document, status = true)
                )
            })
        return this.documentListener
    }

    fun collection(): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .get()
            .addOnSuccessListener { document ->
                this.collection.value = Resource.Success(
                    Response(collection = document, status = true)
                )
            }
            .addOnFailureListener { exception ->
                this.collection.value = Resource.Error(
                    Response(exception = exception)
                )
            }
        return this.collection
    }

    fun collectionListener(): LiveData<Resource<Response>> {
        this.firestore.collection(this.path)
            .addSnapshotListener(EventListener { document, exception ->
                if (exception != null) {
                    this.collectionListener.value = Resource.Error(
                        Response(firestoreException = exception)
                    )
                    return@EventListener
                }
                this.collectionListener.value = Resource.Success(
                    Response(collection = document, status = true)
                )
            })
        return this.collectionListener
    }
}