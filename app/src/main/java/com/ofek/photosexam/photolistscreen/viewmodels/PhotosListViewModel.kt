package com.ofek.photosexam.photolistscreen.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ofek.photosexam.objects.uiobjects.UiPhoto
import com.ofek.photosexam.photolistscreen.repositories.PhotosListRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PhotosListViewModel(val photosListRepo: PhotosListRepo) : ViewModel() {
    val stateLiveData = MutableLiveData<PhotosListScreenState>()
    val compositeDisposable = CompositeDisposable()
    init {
        stateLiveData.value = PhotosListScreenState(0,50,ArrayList(),ArrayList(),false)
    }

    fun loadMorePhotos() {
        val currentState = stateLiveData.value!!
        // the start index is the last load's end index or 0 if no photos has loaded
        photosListRepo.loadPhotos(
            currentState.currentMaxIndex,
            currentState.currentMaxIndex + currentState.loadingCount
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<UiPhoto>> {
                override fun onSubscribe(d:Disposable) {
                    compositeDisposable.add(d)
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

    fun addPhotoToFavorite(photoId: String) {

    }

}