package com.superappli.lafourchette.injection.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.data.source.Local;
import com.superappli.lafourchette.data.source.Remote;
import com.superappli.lafourchette.data.source.RestosDataSource;
import com.superappli.lafourchette.data.source.local.DbOpenHelper;
import com.superappli.lafourchette.data.source.local.RestosLocalDataSource;
import com.superappli.lafourchette.data.source.remote.GsonConverter;
import com.superappli.lafourchette.data.source.remote.LaFourchetteWS;
import com.superappli.lafourchette.data.source.remote.RestosRemoteDataSource;
import com.superappli.lafourchette.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    LaFourchetteWS provideWS(Gson gson) {
        return LaFourchetteWS.Creator.newService(gson);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Resto.class, new GsonConverter<Resto>())
                .create();
    }

    @Singleton
    @Provides
    @Local
    RestosDataSource provideTasksLocalDataSource(BriteDatabase briteDatabase) {
        return new RestosLocalDataSource(briteDatabase);
    }

    @Singleton
    @Provides
    @Remote
    RestosDataSource provideTasksRemoteDataSource(LaFourchetteWS api) {
        return new RestosRemoteDataSource(api);
    }

    @Provides
    @Singleton
    BriteDatabase provideBriteDatabase(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder builder = new SqlBrite.Builder();
        return builder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
    }
}
