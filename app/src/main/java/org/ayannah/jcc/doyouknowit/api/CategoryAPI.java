package org.ayannah.jcc.doyouknowit.api;

import org.ayannah.jcc.doyouknowit.models.Category;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryAPI {

    @GET("api/category/")
    Call<Category> getCategory(@Query("id") int id);
}
