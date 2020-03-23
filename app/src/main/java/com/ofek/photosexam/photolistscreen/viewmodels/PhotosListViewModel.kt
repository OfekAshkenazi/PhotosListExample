package com.ofek.photosexam.photolistscreen.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ofek.photosexam.objects.uiobjects.UiPhoto
import com.ofek.photosexam.photolistscreen.repositories.PhotosListRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class PhotosListViewModel(val photosListRepo: PhotosListRepo) : ViewModel() {
    val stateLiveData = MutableLiveData<PhotosListScreenState>()
    val compositeDisposable = CompositeDisposable()
    init {
        // setting initial state to prevent nullability of the live data value
        stateLiveData.value = PhotosListScreenState(0,50,ArrayList(),false)
    }
    fun loadMorePhotos() {
        val currentState = stateLiveData.value!!
        // the start index is the last load's end index or 0 if no photos has loaded
        photosListRepo.loadPhotos(
            currentState.currentMaxIndex,
            currentState.limit
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<UiPhoto>> {
                override fun onSubscribe(d:Disposable) {
                    compositeDisposable.add(d)
                    // starting loading state
                    val newState = stateLiveData.value!!.copy(isLoading = true)
                    stateLiveData.value = newState
                }

                override fun onSuccess(newPhotos: List<UiPhoto>) {
                    val state = stateLiveData.value!!
                    stateLiveData.value = state.copy(photoList = state.addNewPhotosToList(newPhotos),isLoading = false)
                }

                override fun onError(e: Throwable) {
                    val state = stateLiveData.value!!
                    stateLiveData.value = state.copy(isLoading = false)
                    // handle error?
                    e.printStackTrace()
                }

            })
    }
    fun setFavorite(photoId: String) {
        val currentState = stateLiveData.value!!
        val photosList = currentState.getPhotosList()
        val photo = photosList.find {
            it.uuid.toString() == photoId
        }
        if (photo != null) {
            photo.isFavorite = true
        }
        // the value of the live data has to change, otherwise the observer won't be notified
        stateLiveData.value = currentState.copy(photoList = photosList)
    }

    fun removeFromFavorites(photoId: String) {
        val currentState = stateLiveData.value!!
        val photosList = currentState.getPhotosList()
        val photo = photosList.find {
            it.uuid.toString() == photoId
        }
        if (photo != null) {
            photo.isFavorite = false
        }
        stateLiveData.value = currentState.copy(photoList = photosList)
    }

}
