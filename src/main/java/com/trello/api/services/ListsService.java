package com.trello.api.services;

import com.trello.api.models.Card;
import com.trello.api.models.TrelloList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ListsService {


    @GET("lists/{id}")
    Call<TrelloList> getList(@Path("id") String id);

    @POST("lists")
    Call<TrelloList> createList(@Query("idBoard") String idBoard, @Query("name") String name);

    @PUT("lists/{id}")
    Call<TrelloList> updateList(@Path("id") String id, @Body TrelloList trelloList);

    @PUT("lists/{id}/closed")
    Call<ResponseBody> archiveList(@Path("id") String id, @Query("value") Boolean value);


    @GET("lists/{id}/cards")
    Call<List<Card>> getCards(@Path("id") String id);
}