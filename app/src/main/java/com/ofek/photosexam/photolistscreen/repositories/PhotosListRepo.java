package com.ofek.photosexam.photolistscreen.repositories;

import com.ofek.photosexam.objects.uiobjects.UiPhoto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PhotosListRepo {
    Single<List<UiPhoto>> loadPhotos(int startIndex, int endIndex);
}
