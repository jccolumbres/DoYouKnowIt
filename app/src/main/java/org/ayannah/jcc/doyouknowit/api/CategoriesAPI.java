package org.ayannah.jcc.doyouknowit.api;

import org.ayannah.jcc.doyouknowit.models.Categories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriesAPI {


    @GET("/api/categories/?count={value}")
    Call<Categories> getCategories (@Path("value") String value);


}
