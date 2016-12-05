package com.superappli.lafourchette.data.source;

import com.superappli.lafourchette.data.model.Resto;

import rx.Observable;

/**
 * Interface pour intéragir avec les restos
 * Created by raphael on 04/12/2016.
 */

public interface RestosDataSource {

    Observable<Resto> getResto(int restoId);

    void saveResto(Resto resto);
}
