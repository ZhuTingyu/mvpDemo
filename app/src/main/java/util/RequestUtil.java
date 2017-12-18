package util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zhu TingYu on 2017/12/15.
 */

public class RequestUtil<T> {

    private static final int DEFAULT_TIMEOUT = 5;

    private Map<String, String> bodyParameter = new HashMap<>();
    private Map<String, String> headParameter = new HashMap<>();

    private String url;
    private String baseUrl;
    private String headUrl;

    private Retrofit retrofit;

    private OkHttpClient okHttpClient;
    private RequestInterface requestInterface;

    private String sign;

    public RequestUtil<T> builder() {
        RequestUtil<T> requestUtil = new RequestUtil<>();

        return requestUtil;
    }

    public RequestUtil<T> addBody(String key, String value) {
        bodyParameter.put(key, value);
        return this;
    }

    public RequestUtil<T> addHead(String key, String value) {
        bodyParameter.put(key, value);
        return this;
    }

    public void request() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BaseInterceptor(headParameter))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        requestInterface = retrofit.create(RequestInterface.class);


    }

    public RequestUtil<T> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this;

    }

    RequestInterface getRequestInterface() {
        return requestInterface;
    }

    String getRequestUrl(){
        return headUrl + baseUrl;
    }

    String getSign(){
        //TODO 计算签名
        return "";
    }

    public Map<String, String> getBodyParameter() {
        return bodyParameter;
    }
}
