package com.ofek.photosexam.photolistscreen.repositories;

import com.ofek.photosexam.common.Constants;
import com.ofek.photosexam.managers.PhotosApiManager;
import com.ofek.photosexam.objects.uiobjects.UiPhoto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * an implementation class to {@link PhotosListRepo}
 * repository is used to apply logic on the data if necessary.
 * an example to logic that could be applied in this repository if it was necessary is to save the photos list to internal cache.
 * as it's very simple application there's not much logic to apply.
 */
public class PhotosListRepoImp implements PhotosListRepo {

    private final PhotosApiManager photosApiManager;

    public PhotosListRepoImp(PhotosApiManager photosApiManager) {
        this.photosApiManager = photosApiManager;
    }

    @Override
    public Single<List<UiPhoto>> loadPhotos(int startIndex, int endIndex) {
        return photosApiManager.loadPhotos(startIndex,endIndex,true);
    }
}
