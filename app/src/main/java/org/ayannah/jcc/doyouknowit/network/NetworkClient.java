package org.ayannah.jcc.doyouknowit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static String BASE_URL = "http://jservice.io/";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildConnection(Class<S> apiType){
        return retrofit.create(apiType);
    }
}
