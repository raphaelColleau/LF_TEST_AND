package com.superappli.lafourchette.data.source.local;

import com.squareup.sqlbrite.BriteDatabase;
import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.data.source.RestosDataSource;

import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by raphael on 04/12/2016.
 */

@Singleton
public class RestosLocalDataSource implements RestosDataSource {

    private final BriteDatabase mDatabase;

    public RestosLocalDataSource(BriteDatabase briteDatabase) {
        mDatabase = briteDatabase;
    }

    @Override
    public Observable<Resto> getResto(int restoId) {
        return Observable.empty();
        //TODO query and map resto
//        return mDatabase.createQuery(DbOpenHelper.TABLE_RESTO, "").
//                mapToOneOrDefault(new Func1<Cursor, Resto>() {
//                    @Override
//                    public Resto call(Cursor cursor) {
//                        if (cursor.moveToFirst()) {
//                            Resto resto = new Resto();
//                            resto.setName(cursor.getString(0));
//                            return resto;
//                        }
//                        return null;
//                    }
//                }, null);
    }

    @Override
    public void saveResto(Resto resto) {
        //TODO insert resto in db
//        mDatabase.insert(DbOpenHelper.TABLE_RESTO, contentValues);
    }
}
