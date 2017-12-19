package util;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import model.entity.ApiResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zhu TingYu on 2017/12/15.
 */

public class RxRequest {
    public static Observable<String> getRxHttp(RequestUtil rxRequest) {
        return Observable.<String>create(observableEmitter -> {
            Map<String, String> body = rxRequest.getBodyParameter();
            Map<String, String> files = rxRequest.getFileParameter();

            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            for (String key : body.keySet()) {
                builder.addFormDataPart(key, body.get(key));
            }

            if(files.size() > 0){
                for(String key : body.keySet()){
                    File file = new File(files.get(key));
                    if(file.exists()){
                        builder.addPart(MultipartBody.Part.createFormData(key,file.getName(),
                                RequestBody.create(MediaType.parse("image/*"), file)));
                    }

                }
            }

            Call<ResponseBody> call = rxRequest.getRequestInterface().request(rxRequest.getUrl(), String.valueOf(rxRequest.getUid()), rxRequest.getSign(), builder.build());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                         observableEmitter.onNext(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    ApiResponse apiResponse = new ApiResponse();
                    apiResponse.errorCode = -1;
                    apiResponse.msg = "服务器内部异常";
                    observableEmitter.onNext(GsonUtil.toJson(apiResponse));
                }
            });
        });
    }
}
