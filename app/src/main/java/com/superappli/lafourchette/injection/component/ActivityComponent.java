package com.superappli.lafourchette.injection.component;

import com.superappli.lafourchette.injection.PerActivity;
import com.superappli.lafourchette.injection.module.ActivityModule;
import com.superappli.lafourchette.ui.restoDetails.RestoDetailsActivity;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RestoDetailsActivity restoDetailsActivity);

}
