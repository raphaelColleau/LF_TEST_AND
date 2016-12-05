package com.superappli.lafourchette;

import android.app.Application;
import android.content.Context;

import com.superappli.lafourchette.injection.component.ApplicationComponent;
import com.superappli.lafourchette.injection.component.DaggerApplicationComponent;
import com.superappli.lafourchette.injection.module.ApplicationModule;

/**
 * Created by raphael on 04/12/2016.
 */

public class LaFourchetteApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public static LaFourchetteApplication get(Context context) {
        return (LaFourchetteApplication) context.getApplicationContext();
    }
}
