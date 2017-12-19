package model;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import model.entity.ApiResponse;
import model.entity.ProjectContentEntity;
import util.HttpUtil;
import util.RequestUtil;

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

    @Override
    public Observable<ApiResponse<String>> demoInfo(){
        return RequestUtil.<ApiResponse<String>>builder()
                .setBaseUrl(" http://118.123.244.89:818")
                .headUrl("/CPAPI/V1/")
                .url("GetGXT_UserInfo")
                .setToJsonType(new TypeToken<ApiResponse<String>>(){}.getType())
                .request();
    }

}
