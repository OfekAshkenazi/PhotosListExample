package com.ofek.photosexam.photolistscreen.viewmodels

import com.ofek.photosexam.objects.uiobjects.UiPhoto

data class PhotosListScreenState constructor (
    val currentMaxIndex: Int,
    val loadingCount: Int,
    val photoList: List<UiPhoto>,
    val favoritePhotos: List<UiPhoto>,
    val isLoading:Boolean
) {
    /**
     * adds new photos to a copy of the current photos list and returns the copy
     * an helper function to add new photos to the list while keeping the current list immutable
     */
    fun addNewPhotosToList(toInsert:List<UiPhoto> ): List<UiPhoto> {
        val newList = ArrayList(photoList)
        newList.addAll(toInsert)
        return newList
    }

    /**
     * an helper function to add a new photo to favorite list and maintain immutability of the photos list
     */
    fun addPhotoToFavoriteList(toInsert : UiPhoto): List<UiPhoto> {
        val newList = ArrayList(favoritePhotos)
        newList.add(toInsert)
        return newList
    }

    /**
     * an helper function to remove a photo from the list by it's ID and maintain the list's immutability
     */
    fun removePhotoFromPhotosList(photoId: String): List<UiPhoto>  {
        val newList = ArrayList(photoList)
        val tempUiPhoto = UiPhoto()
        // creating a temp obj with the id in order to remove it from the list using the Object.equals() function
        tempUiPhoto.id = photoId
        newList.remove(tempUiPhoto)
        return newList
    }

}