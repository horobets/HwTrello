package com.trello.api.services;

import com.trello.api.models.Card;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by horobets on Jun 25, 2019
 */
public interface CardsService {


    @GET("cards/{id}?fields=all")
    Call<Card> getCard(@Path("id") String id);

    @POST("cards")
    Call<Card> createCard(@Query("idList") String idList, @Body Card card);

    @PUT("cards/{id}")
    Call<Card> updateCard(@Path("id") String id, @Body Card card);

    @DELETE("cards/{id}")
    Call<ResponseBody> deleteCard(@Path("id") String id);


    @POST("cards/{id}/labels")
    Call<ResponseBody> addLabel(@Path("id") String id, @Query("color") LabelColor color, @Query("name") String name);

    @POST("cards/{id}/idLabels")
    Call<ResponseBody> addIdLabel(@Path("id") String id, @Query("value") String value);


    @DELETE("cards/{id}/idLabels/{idLabel}")
    Call<ResponseBody> removeIdLabel(@Path("id") String id, @Path("idLabel") String idLabel);

}