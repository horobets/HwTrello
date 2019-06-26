package com.trello.api.services;

import com.trello.api.models.Card;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by horobets on Jun 25, 2019
 */
public interface CardsService {

    @POST("cards")
    Call<Card> createCard(@Query("idList") String idList, @Body Card card);

    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @DELETE("cards/{id}")
    Call<ResponseBody> deleteCard(@Path("id") String id);


}