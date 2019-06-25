package com.trello.api.services;

import com.trello.api.models.Card;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by horobets on Jun 25, 2019
 */
public class CardsService {

    @POST("card")
    Call<Card> createCard(@Query("idList") String idList, @Body Card card);
    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);
}
