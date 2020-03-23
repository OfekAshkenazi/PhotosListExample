package com.ofek.photosexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ofek.photosexam.objects.uiobjects.UiPhoto
import com.ofek.photosexam.photolistscreen.fragments.PhotosListFragment
import com.ofek.photosexam.singlephotoscreen.fragments.SinglePhotoFragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(),PhotosListFragment.PhotoSelectionListener,SinglePhotoFragment.SinglePhotoInteractionListener{
    private val SINGLE_PHOTO_FRAGMENT_TAG = "single_photo_fragment"
    val photosListFragment = PhotosListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container,photosListFragment).commit()
    }

    override fun onPhotoSelected(uiPhoto: UiPhoto) {
        supportFragmentManager.beginTransaction().replace(R.id.container,SinglePhotoFragment.newInstance(uiPhoto)).addToBackStack(SINGLE_PHOTO_FRAGMENT_TAG).commit()
    }

    override fun onFavoriteSelectionChanged(id: String, isFavorite: Boolean) {
        photosListFragment.onFavoriteSelectionChanged(id,isFavorite)
    }


}
