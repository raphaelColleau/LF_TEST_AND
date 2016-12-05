package com.superappli.lafourchette.ui.restoDetails;

import android.support.annotation.StringRes;

import com.superappli.lafourchette.data.model.Resto;
import com.superappli.lafourchette.ui.base.MvpView;

/**
 * Created by raphael on 04/12/2016.
 */

public interface RestoDetailsMvpView extends MvpView {

    void showLoading(boolean loading);
    void showResto(Resto resto);
    void showToast(@StringRes int text);
    void showError(@StringRes int text);
    void startShareIntent(String url);
}
