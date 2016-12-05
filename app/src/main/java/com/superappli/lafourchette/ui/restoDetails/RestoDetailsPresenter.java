package com.superappli.lafourchette.ui.restoDetails;

import android.util.Log;

import com.superappli.lafourchette.R;
import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.data.source.RestosDataSource;
import com.superappli.lafourchette.data.source.RestosRepository;
import com.superappli.lafourchette.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by raphael on 03/12/2016.
 */

public class RestoDetailsPresenter extends BasePresenter<RestoDetailsMvpView> {

    private static final int[] restoIds = new int[]{6861, 40370, 16409, 14163};

    private int restoPosition = 0;
    private Resto mResto;
    private RestosDataSource mRestosDataSource;
    private Subscription mSubscription;

    @Inject
    RestoDetailsPresenter(RestosRepository restosDataSource) {
        mRestosDataSource = restosDataSource;
    }

    @Override
    public void attachView(RestoDetailsMvpView mvpView) {
        super.attachView(mvpView);
        getMvpView().showLoading(true);
        loadResto();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    private void loadResto() {
        mSubscription = mRestosDataSource.getResto(restoIds[restoPosition])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Resto>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().showLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(R.string.error_loading);
                    }

                    @Override
                    public void onNext(Resto resto) {
                        mResto = resto;
                        getMvpView().showResto(resto);
                    }
                });
    }

    void onClickFav() {
        getMvpView().showToast(R.string.action_fav);
    }

    void onClickShare() {
        if (mResto != null)
            getMvpView().startShareIntent(mResto.getPortal_url());
    }

    void changeResto() {
        restoPosition = (restoPosition+1) % restoIds.length;
        loadResto();
    }
}
