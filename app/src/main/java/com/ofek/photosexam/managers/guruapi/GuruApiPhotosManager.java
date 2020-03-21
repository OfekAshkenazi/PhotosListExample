package com.ofek.photosexam.managers.guruapi;

import android.util.Log;

import com.ofek.photosexam.common.Constants;
import com.ofek.photosexam.managers.PhotosApiManager;
import com.ofek.photosexam.objects.guruapiobjects.GuruApiResponse;
import com.ofek.photosexam.objects.mappers.DtoToUiMappers;
import com.ofek.photosexam.objects.uiobjects.UiPhoto;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Response;

public class GuruApiPhotosManager implements PhotosApiManager {

    private static final String LOG_TAG = "GuruApiError";
    private final GuruApiService guruApiService;

    public GuruApiPhotosManager(GuruApiService guruApiService) {
        this.guruApiService = guruApiService;
    }


    @Override
    public Single<List<UiPhoto>> loadPhotos(int startIndex, int limit, boolean getLikes) {
        return Single.fromCallable(()->{
            Call<GuruApiResponse> call = guruApiService.getPhotosList(Constants.GURU_API_MEMBER_ID,startIndex, limit, getLikes);
            Response<GuruApiResponse> response = call.execute();
            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                return response.body().getItems();
            } else {
                // handle error
                Log.e(LOG_TAG, "loadPhotos: "+response.toString());
                throw new Exception(response.toString());
            }
        })
                // applies mapping to all list's items
                .toObservable().flatMap(Observable::fromIterable).map(DtoToUiMappers::photoMapper).toList();
    }
}