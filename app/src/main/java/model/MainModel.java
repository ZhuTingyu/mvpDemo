package model;

import io.reactivex.Observable;
import model.entity.ApiResponse;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface MainModel {
    Observable<String> getData();
    Observable<ApiResponse<String>> demoInfo();
}
