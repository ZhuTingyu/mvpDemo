package util;

import java.util.Map;

import io.reactivex.Observable;
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

            Call<String> call = rxRequest.getRequestInterface().request(rxRequest.getRequestUrl(),rxRequest.getSign(), builder.build());

            Response<String> response = call.execute();

        });
    }
}
