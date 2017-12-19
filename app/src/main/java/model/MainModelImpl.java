package model;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import model.entity.ApiResponse;
import model.entity.ProjectEntity;
import util.HttpUtil;
import util.RequestUtil;

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
    @Override
    public Observable<ApiResponse<String>> demoInfo(){
        return RequestUtil.<ApiResponse<String>>builder()
                .setBaseUrl(" http://118.123.244.89:818")
                .headUrl("/CPAPI/V1/")
                .setToJsonType(new TypeToken<ApiResponse<String>>(){}.getType())
                .url("GetGXT_UserInfo")
                .addBody("uid","1231")
                .setToJsonType(new TypeToken<ApiResponse<String>>(){}.getType())
                .request();
    }
}
