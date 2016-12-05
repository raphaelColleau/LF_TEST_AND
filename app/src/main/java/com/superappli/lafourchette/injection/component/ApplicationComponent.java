package com.superappli.lafourchette.injection.component;

import android.app.Application;
import android.content.Context;

import com.superappli.lafourchette.injection.ApplicationContext;
import com.superappli.lafourchette.injection.module.ActivityModule;
import com.superappli.lafourchette.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    @ApplicationContext
    Context context();
    Application application();
}
