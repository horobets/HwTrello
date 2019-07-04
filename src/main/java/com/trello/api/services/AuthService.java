package com.trello.api.services;

import com.trello.api.models.AuthResponseData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {


    @GET("https://trello.com/")
    Call<ResponseBody> homepage();

    @FormUrlEncoded
    @POST("https://trello.com/1/authentication")
    Call<AuthResponseData> authentication(@Field("method") String method, @Field("factors[user]") String user, @Field("factors[password]") String password);

    @FormUrlEncoded
    @POST("https://trello.com/1/authorization/session")
    Call<ResponseBody> session(@Field("authentication") String authentication, @Field("dsc") String dsc);

}
