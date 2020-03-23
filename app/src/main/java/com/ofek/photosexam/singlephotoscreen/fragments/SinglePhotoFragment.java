package com.ofek.photosexam.singlephotoscreen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.ofek.photosexam.R;
import com.ofek.photosexam.common.Constants;
import com.ofek.photosexam.objects.uiobjects.UiPhoto;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SinglePhotoFragment extends Fragment {

    private static final String PHOTO_KEY = "photo";
    private UiPhoto photo;
    private AppCompatImageView favBtnIv;
    private AppCompatImageView mainPhotoIV;
    private AppCompatTextView likeCountTv;
    private AppCompatTextView viewsCountTv;
    private SinglePhotoInteractionListener listener;

    public static SinglePhotoFragment newInstance(UiPhoto photo) {
        Bundle args = new Bundle();
        args.putParcelable(PHOTO_KEY,photo);
        SinglePhotoFragment fragment = new SinglePhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SinglePhotoInteractionListener) {
            listener = (SinglePhotoInteractionListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getParcelable(PHOTO_KEY) != null) {
            photo = getArguments().getParcelable(PHOTO_KEY);
        } else {
            throw new IllegalArgumentException("No photo in arguments");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_photo_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favBtnIv = view.findViewById(R.id.like_btn_photo_item);
        mainPhotoIV = view.findViewById(R.id.main_iv_photo_item);
        likeCountTv = view.findViewById(R.id.likes_count_tv_photo_item);
        viewsCountTv = view.findViewById(R.id.views_count_tv_photo_item);
        favBtnIv.setOnClickListener((favBtn)->{
            if (photo != null) {
                // changes the btn state
                favBtn.setSelected(!favBtn.isSelected());
                // notifies the host activity about the selection change
                listener.onFavoriteSelectionChanged(photo.getUuid().toString(),favBtn.isSelected());
            }
        });
        Picasso.get()
                .load(Constants.buildPhotoUrl(photo.getGuruPhotoId(),photo.getMaxWidth(),photo.getMaxHeight()))
                .placeholder(R.drawable.ic_loader)
                .fit().into(mainPhotoIV);
        likeCountTv.setText(String.format(Locale.US,"%d", photo.getLikes()));
        viewsCountTv.setText(String.format(Locale.US,"%d", photo.getViews()));
        favBtnIv.setSelected(photo.isFavorite());
    }



    public interface SinglePhotoInteractionListener {
        void onFavoriteSelectionChanged(String id, boolean isFavorite);
    }
}
