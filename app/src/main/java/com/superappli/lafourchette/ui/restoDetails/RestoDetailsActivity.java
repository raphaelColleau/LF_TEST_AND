package com.superappli.lafourchette.ui.restoDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.superappli.lafourchette.R;
import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestoDetailsActivity extends BaseActivity implements RestoDetailsMvpView, OnMapReadyCallback {

    @Inject
    RestoDetailsPresenter mPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;

    @BindView(R.id.tv_resto_name) TextView mRestoNameTextView;
    @BindView(R.id.tv_speciality) TextView mSpecialityTextView;
    @BindView(R.id.tv_description) TextView mDescriptionTextView;
    @BindView(R.id.tv_open_hours) TextView mOpenHoursTextView;
    @BindView(R.id.tv_address) TextView mAddressTextView;
    @BindView(R.id.tv_transport) TextView mTransportTextView;
    @BindView(R.id.content) View mContent;

    @BindView(R.id.pager) ViewPager mGalleryViewPager;

    private GoogleMap mMap;
    private String mRestoName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_details);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbarLayout.setTitle(" ");

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(mOnOffsetChangedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickFav();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resto_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_next_resto) {
            mPresenter.changeResto();
            return true;
        }
        if (id == R.id.action_share) {
            mPresenter.onClickShare();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(boolean loading) {
        // TODO faire un loader
        Log.i("mvpView", "loading " + loading);
    }

    @Override
    public void showResto(Resto resto) {
        mContent.setVisibility(View.VISIBLE);
        mRestoName = resto.getName();
        mRestoNameTextView.setText(resto.getName());
        mSpecialityTextView.setText(resto.getSpeciality());
        mDescriptionTextView.setText(resto.getDescription());
        mOpenHoursTextView.setText(resto.getHour_open());
        mAddressTextView.setText(resto.getAddress() + " " + resto.getZipcode() + " " + resto.getCity());
        mTransportTextView.setText(resto.getTransport());

        GalleryViewPagerAdapter adapter = new GalleryViewPagerAdapter(resto.getPics_diaporama());
        mGalleryViewPager.setAdapter(adapter);

        if (mMap != null) {
            mMap.clear();
            LatLng latLng = new LatLng(resto.getGps_lat(), resto.getGps_long());
            mMap.addMarker(new MarkerOptions().position(latLng)
                    .title(resto.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

    @Override
    public void showToast(@StringRes int text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int text) {
        // TODO mettre un bouton de retry
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startShareIntent(String url) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.dialog_title_share)));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    // Pour faire apparaître et disparaître le titre dans la toolbar
    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        boolean isShow = false;
        int scrollRange = -1;

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout.getTotalScrollRange();
            }
            if (scrollRange + verticalOffset == 0) {
                mToolbarLayout.setTitle(mRestoName);
                isShow = true;
            } else if(isShow) {
                mToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                isShow = false;
            }
        }
    };
}
