package com.trello.api.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import com.trello.api.models.Board;
import com.trello.api.models.TrelloList;

import java.util.List;

public interface BoardsService {

    @GET("boards/{id}/lists")
    Call<List<TrelloList>> getLists(@Path("id") String id);

    @POST("boards")
    Call<Board> createBoard(@Query("name") String name);

    @DELETE("boards/{id}")
    Call<ResponseBody> deleteBoard(@Path("id") String id);

    @PUT("boards/{id}")
    Call<Board> updateBoard(@Path("id") String id, @Body Board board);


}