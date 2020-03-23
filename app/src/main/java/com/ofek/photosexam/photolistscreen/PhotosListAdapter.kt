package com.ofek.photosexam.photolistscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ofek.photosexam.R
import com.ofek.photosexam.common.Constants
import com.ofek.photosexam.objects.uiobjects.UiPhoto
import com.squareup.picasso.Picasso

class PhotosListAdapter(
    private var photosList: List<UiPhoto>,
    private val listener: PhotosAdapterInteractionListener
) : RecyclerView.Adapter<PhotosListAdapter.PhotoVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_list_item, parent, false)
        return PhotoVH(view)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        val item = photosList[position]
        holder.favBtnIv.isSelected = item.isFavorite
        holder.favBtnIv.setOnClickListener {
            listener.onFavoriteSelectionChanged(item.uuid.toString(), !item.isFavorite)
        }
        holder.mainPhotoIv.setOnClickListener {
            listener.onPhotoSelected(item)
        }
        holder.likeCountTv.text = item.likes.toString()
        holder.viewCountTv.text = item.views.toString()
        Picasso.get()
            .load(Constants.buildPhotoUrl(item.guruPhotoId, item.maxWidth, item.maxWidth))
            .placeholder(R.drawable.ic_loader).fit().into(holder.mainPhotoIv)
    }

    /**
     * call this function to update the photos list
     */
    fun setPhotosList(photosList: List<UiPhoto>) {
        this.photosList = photosList;
        notifyDataSetChanged()
    }

    interface PhotosAdapterInteractionListener {
        fun onPhotoSelected(photo: UiPhoto)
        fun onFavoriteSelectionChanged(id: String, isFavorite: Boolean)
    }

    class PhotoVH(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val likeCountTv: AppCompatTextView = itemView.findViewById(R.id.likes_count_tv_photo_item)
        val viewCountTv: AppCompatTextView = itemView.findViewById(R.id.views_count_tv_photo_item)
        val favBtnIv: AppCompatImageView = itemView.findViewById(R.id.like_btn_photo_item)
        val mainPhotoIv: AppCompatImageView = itemView.findViewById(R.id.main_iv_photo_item)
    }
}


