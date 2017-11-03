package model;

import io.reactivex.Observable;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface ProjectContentModel {
    Observable<String> getData(String id);
}
