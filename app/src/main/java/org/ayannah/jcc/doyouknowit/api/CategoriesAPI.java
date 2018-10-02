package org.ayannah.jcc.doyouknowit.api;

import org.ayannah.jcc.doyouknowit.models.Categories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoriesAPI {


    @GET("/api/categories/")
    Call<List<Categories>> getCategories (@Query("count") int value);


}
