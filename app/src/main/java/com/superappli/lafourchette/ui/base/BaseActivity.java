package com.superappli.lafourchette.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.superappli.lafourchette.LaFourchetteApplication;
import com.superappli.lafourchette.injection.component.ActivityComponent;
import com.superappli.lafourchette.injection.module.ActivityModule;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DaggerConfigPersistentComponent.builder()
//                .applicationComponent(BoilerplateApplication.get(this).getComponent())
//                .build();
//        mActivityComponent = getApplication().activityComponent(new ActivityModule(this));
//        mActivityComponent = Dagger LaFourchetteApplication.get(this).getComponent()
        mActivityComponent = LaFourchetteApplication.get(this).getComponent().activityComponent(new ActivityModule(this));
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

}
