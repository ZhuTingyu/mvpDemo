package util;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Zhu TingYu on 2017/12/15.
 */

public interface RequestInterface {
    @POST
    Call<String> request(
            @Url String url,
            @Query("sign") String sign,
            @Body RequestBody requestBody);
}
