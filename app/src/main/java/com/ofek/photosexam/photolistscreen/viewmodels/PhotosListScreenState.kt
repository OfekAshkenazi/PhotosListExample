package com.ofek.photosexam.photolistscreen.viewmodels

import com.ofek.photosexam.objects.uiobjects.UiPhoto
import java.util.*
import kotlin.collections.ArrayList

data class PhotosListScreenState constructor (
    val currentMaxIndex: Int,
    val limit: Int,
    private val photoList: List<UiPhoto>,
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

    fun getPhotosList(): List<UiPhoto> {
        return ArrayList(photoList)
    }

}