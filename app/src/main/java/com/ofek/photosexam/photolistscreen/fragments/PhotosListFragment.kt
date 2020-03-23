package com.ofek.photosexam.photolistscreen.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import com.ofek.photosexam.R
import com.ofek.photosexam.common.Constants
import com.ofek.photosexam.managers.guruapi.GuruApiPhotosManager
import com.ofek.photosexam.managers.guruapi.GuruApiService
import com.ofek.photosexam.objects.uiobjects.UiPhoto
import com.ofek.photosexam.photolistscreen.PhotosListAdapter
import com.ofek.photosexam.photolistscreen.repositories.PhotosListRepoImp
import com.ofek.photosexam.photolistscreen.viewmodels.PhotosListScreenState
import com.ofek.photosexam.photolistscreen.viewmodels.PhotosListViewModel
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import java.lang.IllegalStateException

/**
 * A simple [Fragment] subclass.
 */
class PhotosListFragment : Fragment(),PhotosListAdapter.PhotosAdapterInteractionListener {
    private lateinit var photosRV : RecyclerView
    private lateinit var listener : PhotoSelectionListener
    private lateinit var loadMoreLay: SmartRefreshLayout
    private val adapter = PhotosListAdapter(ArrayList(),this)
    private val photosListVM = PhotosListViewModel(PhotosListRepoImp(GuruApiPhotosManager(Constants.GURU_SERVICE)))

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is PhotoSelectionListener) {
            listener = context;
        } else {
            throw IllegalStateException("Host activity doesn't implement interaction listener")
        }
        photosListVM.stateLiveData.observe(this,observer)
        // start loading the first time automatically
        photosListVM.loadMorePhotos()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photosRV = view.findViewById(R.id.photos_list_rv)
        photosRV.adapter = adapter
        photosRV.layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
        loadMoreLay = view.findViewById(R.id.load_more_layout)
        loadMoreLay.setOnLoadMoreListener {
            photosListVM.loadMorePhotos()
        }
    }

    /**
     * the listener to the state changes.
     */
    private val observer : Observer<PhotosListScreenState> = Observer {
        adapter.setPhotosList(it.getPhotosList())
        if (!it.isLoading) {
            loadMoreLay.finishLoadMore()
        }
    }
    override fun onPhotoSelected(photo: UiPhoto) {
        listener.onPhotoSelected(uiPhoto = photo)
    }

    override fun onFavoriteSelectionChanged(id: String, isFavorite: Boolean) {
        if(isFavorite) {
            photosListVM.setFavorite(id)
        } else {
            photosListVM.removeFromFavorites(id)
        }
    }

    interface PhotoSelectionListener {
        fun onPhotoSelected(uiPhoto: UiPhoto)
    }
}
