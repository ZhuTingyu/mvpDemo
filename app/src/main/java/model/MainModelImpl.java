package model;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import model.entity.ProjectEntity;
import util.HttpUtil;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class MainModelImpl implements MainModel {
    @Override
    public Observable<String> getData() {
        return HttpUtil.builder()
                .type(new TypeToken<List<ProjectEntity>>(){}.getType())
                .url("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111")
                .post();
    }
}
