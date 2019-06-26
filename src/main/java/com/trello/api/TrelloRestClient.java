package com.trello.api;

import com.trello.api.interceptors.AddCookiesInterceptor;
import com.trello.api.interceptors.HTTPLogInterceptor;
import com.trello.api.interceptors.ReceivedCookiesInterceptor;
import com.trello.api.interceptors.TrelloAuthInterceptor;
import com.trello.api.services.AuthService;
import com.trello.api.services.BoardsService;
import com.trello.api.services.CardsService;
import com.trello.api.services.ListsService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class TrelloRestClient {

    public static final String HOME_IO_BASE_URL = "https://api.trello.com/1/";


    public BoardsService boardsService;
    public ListsService listsService;
    public CardsService cardsService;

    public AuthService authService;


    public TrelloRestClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new TrelloAuthInterceptor())
                .addInterceptor(new HTTPLogInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOME_IO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        boardsService = retrofit.create(BoardsService.class);
        listsService = retrofit.create(ListsService.class);
        cardsService = retrofit.create(CardsService.class);

        authService = retrofit.create(AuthService.class);
    }


    public TrelloRestClient(HashSet<String> preferences) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new TrelloAuthInterceptor())
                .addInterceptor(new HTTPLogInterceptor())
                .addInterceptor(new AddCookiesInterceptor(preferences))
                .addInterceptor(new ReceivedCookiesInterceptor(preferences))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOME_IO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        boardsService = retrofit.create(BoardsService.class);
        listsService = retrofit.create(ListsService.class);
        cardsService = retrofit.create(CardsService.class);

        authService = retrofit.create(AuthService.class);
    }
}