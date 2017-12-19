package model;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import model.entity.ApiResponse;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface ProjectContentModel {
    Observable<String> getData(String id);
    Observable<ApiResponse<String>> demoInfo();
}
