package util;

import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import model.entity.ApiResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Zhu TingYu on 2017/12/15.
 */

public class RxRequest {
    public static Observable<String> getRxHttp(RequestUtil rxRequest){
        return Observable.<String>create(observableEmitter -> {
            Map<String, String> body = rxRequest.getBodyParameter();

            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            for (String key : body.keySet()) {
                builder.addFormDataPart(key, body.get(key));
            }

            Call<ApiResponse<Object>> call = rxRequest.getRequestInterface().request(rxRequest.getUrl(),"",rxRequest.getSign(), builder.build());

            Response<ApiResponse<Object>> response = call.execute();

            if(response.code() == 200){
                observableEmitter.onNext(response.body().toJsonString());
            }else {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.errorCode = -1;
                apiResponse.msg = "服务器内部异常";
                observableEmitter.onNext(GsonUtil.toJson(apiResponse));
            }

        });
    }
}
