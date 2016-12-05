package com.superappli.lafourchette.data.source;

import com.superappli.lafourchette.data.model.Resto;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by raphael on 04/12/2016.
 */

@Singleton
public class RestosRepository implements RestosDataSource {

    private RestosDataSource mRemoteRestosDataSource;
    private RestosDataSource mLocalRestosDataSource;

    @Inject
    RestosRepository(@Remote RestosDataSource remoteRestosDataSource, @Local RestosDataSource localRestosDataSource) {
        mRemoteRestosDataSource = remoteRestosDataSource;
        mLocalRestosDataSource = localRestosDataSource;
    }

    @Override
    public Observable<Resto> getResto(int restoId) {
        // on interroge la base
        // si pas de résultats, on appelle le ws et on enregistre le résultat en base
        return Observable.concat(
                mLocalRestosDataSource.getResto(restoId),
                getAndSaveRemoteResto(restoId))
                .first();
    }

    @Override
    public void saveResto(Resto resto) {
        mLocalRestosDataSource.saveResto(resto);
    }

    private Observable<Resto> getAndSaveRemoteResto(int restoId) {
        return mRemoteRestosDataSource.getResto(restoId)
                .doOnNext(new Action1<Resto>() {
                    @Override
                    public void call(Resto resto) {
                        mLocalRestosDataSource.saveResto(resto);
                    }
                });
    }
}
