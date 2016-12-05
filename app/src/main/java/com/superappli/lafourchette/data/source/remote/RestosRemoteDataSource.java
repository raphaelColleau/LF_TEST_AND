package com.superappli.lafourchette.data.source.remote;

import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.data.source.RestosDataSource;

import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by raphael on 04/12/2016.
 */
@Singleton
public class RestosRemoteDataSource implements RestosDataSource {

    private LaFourchetteWS api;

    public RestosRemoteDataSource(LaFourchetteWS api) {
        this.api = api;
    }

    @Override
    public Observable<Resto> getResto(int restoId) {
        return api.getRestoDetails(restoId);
    }

    @Override
    public void saveResto(Resto resto) {
        // pas besoin
    }
}
