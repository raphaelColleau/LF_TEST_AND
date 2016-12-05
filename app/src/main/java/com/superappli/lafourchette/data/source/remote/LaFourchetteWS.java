package com.superappli.lafourchette.data.source.remote;

import com.google.gson.Gson;
import com.superappli.lafourchette.data.model.Resto;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * interface du WS
 * Created by raphael on 03/12/2016.
 */

public interface LaFourchetteWS {

    String ENDPOINT = "http://api.lafourchette.com/";

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static LaFourchetteWS newService(Gson gson) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LaFourchetteWS.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(LaFourchetteWS.class);
        }
    }

    @GET("api?key=IPHONEPRODEDCRFV&method=restaurant_get_info")
    Observable<Resto> getRestoDetails(@Query("id_restaurant") int restoId);
}
