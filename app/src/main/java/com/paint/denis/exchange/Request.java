package com.paint.denis.exchange;

import com.google.gson.JsonObject;
import com.paint.denis.exchange.CurrenciesModel.CurrenciesModel;
import com.paint.denis.exchange.ExchangeRatesM.Rate;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Request {
    String BASE_URL = "https://free.currencyconverterapi.com/api/v6/";

    @GET("currencies")
    Call<CurrenciesModel> getCurrencies();

    @GET("convert")
    Call<JsonObject> getExchange(
            @Query("q") String q,
            @Query("compact") String compact);


//    Call<List<News>> getNews(
//            @Query("page") int page,
//            @Query("order") String order,


//https://free.currencyconverterapi.com/api/v6/convert?q=USD_PHP&compact=y

//    @GET("convert?q={first}_RUB&compact=y")
//    //Call<String> getExchange(@Path("first") String first,@Path("second") String second);
//    Call<String> getExchange(@Path("first") String first);

//    @GET("convert?q="+first+"_RUB&compact=y")
//    Call<String> getExchange(@Query("first") String first);


    class Factory {
        private static Retrofit retrofit;

        public static Request createPostsApi() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
            return retrofit.create(Request.class);
        }
    }
}
//https://free.currencyconverterapi.com/api/v6/convert?q=USD_PHP&compact=y
//{"USD_RUB":{"val":65.986038}}
//https://free.currencyconverterapi.com/api/v6/currencies