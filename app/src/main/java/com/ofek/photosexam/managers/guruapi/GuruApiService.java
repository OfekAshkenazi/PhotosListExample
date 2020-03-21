package com.ofek.photosexam.managers.guruapi;

import com.ofek.photosexam.objects.guruapiobjects.GuruApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GuruApiService {

    @FormUrlEncoded
    @Headers(value = {
            "X-API-VERSION:21",
            "X-ENV:ANDROID"
    })
    @POST("get_photos_public")
    Call<GuruApiResponse> getPhotosList(@Field("member_id") String memberId,@Field("start") int start,@Field("limit") int limit,@Field("get_likes") boolean getLikes);
}
