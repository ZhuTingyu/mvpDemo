package model;

import io.reactivex.Observable;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface MainModel {
    Observable<String> getData();
}
