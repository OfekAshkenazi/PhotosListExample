package com.ofek.photosexam.common;

import com.ofek.photosexam.managers.guruapi.GuruApiService;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * an helper class that contains constants and static helper functions
 */
public class Constants {
    public static final String GURU_API_BASE_URL = "https://api.gurushots.com/rest_mobile/";
    public static final String GURU_API_MEMBER_ID = "2a49ab04b1534574e578a08b8f9d7441";
    public static final String GURU_PHOTO_BASE_URL = "https://photos.gurushots.com/unsafe/" ;
    @NotNull
    public static final Retrofit GURU_RETROFIT = new Retrofit.Builder()
            .baseUrl(GURU_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    @NotNull
    public static final GuruApiService GURU_SERVICE = GURU_RETROFIT.create(GuruApiService.class);

    public static String buildPhotoUrl(String id,int width,int height) {
        return Constants.GURU_PHOTO_BASE_URL + width + "x" + height + "/" + Constants.GURU_API_MEMBER_ID + "/"+"3_"+id+".jpg";
    }
}
