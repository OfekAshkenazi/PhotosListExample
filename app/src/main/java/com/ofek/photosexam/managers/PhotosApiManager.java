package com.ofek.photosexam.managers;

import com.ofek.photosexam.common.Constants;
import com.ofek.photosexam.objects.uiobjects.UiPhoto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PhotosApiManager {

    Single<List<UiPhoto>> loadPhotos(int startIndex, int limit, boolean getLikes);

}
