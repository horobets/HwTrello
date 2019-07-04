package com.trello.api.services;

import com.trello.api.models.Label;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface LabelsService {

    @GET("labels/{id}")
    Call<Label> getLabel(@Path("id") String id);

    @POST("labels")
    Call<Label> createLabel(@Query("name") String name, @Query("color") LabelColor color, @Query("idBoard") String idBoard);

    @PUT("labels/{id}")
    Call<Label> updateLabel(@Path("id") String id, @Query("name") String name, @Query("color") LabelColor color);

    @PUT("labels/{id}/color")
    Call<Label> updateColor(@Path("id") String id, @Query("color") LabelColor value);

    @PUT("labels/{id}/name")
    Call<Label> updateName(@Path("id") String id, @Query("value") String value);

    @DELETE("labels/{id}")
    Call<ResponseBody> deleteLabel(@Path("id") String id);
}