package model;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import model.entity.ProjectContentEntity;
import util.HttpUtil;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class ProjectContentModelImpl implements ProjectContentModel {
    @Override
    public Observable<String> getData(String id) {
        return HttpUtil.builder()
                .type(new TypeToken<List<ProjectContentEntity>>(){}.getType())
                .url("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111")
                .addBody("",id)
                .post();
    }
}
