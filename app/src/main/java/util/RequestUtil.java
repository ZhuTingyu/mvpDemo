package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import model.entity.ApiResponse;
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
    private Map<String, String> fileParameter = new HashMap<>();

    private Type toJsonType;

    private String url;
    private String baseUrl;
    private String headUrl;

    private Retrofit retrofit;

    private OkHttpClient okHttpClient;
    private RequestInterface requestInterface;

    private String sign;

    private int uid;

    public static <T> RequestUtil<T> builder() {
        RequestUtil<T> requestUtil = new RequestUtil<>();

        return requestUtil;
    }

    public RequestUtil<T> addBody(String key, String value) {
        bodyParameter.put(key, value);
        return this;
    }

    public RequestUtil<T> addFile(String key, String filePath) {
        fileParameter.put(key, filePath);
        return this;
    }

    public RequestUtil<T> addHead(String key, String value) {
        bodyParameter.put(key, value);
        return this;
    }

    public RequestUtil<T> headUrl(String headUrl) {
        this.headUrl = headUrl;
        return this;
    }

    public RequestUtil<T> url(String url) {
        this.url = url;
        return this;
    }

    public RequestUtil<T> setToJsonType(Type toJsonType) {
        this.toJsonType = toJsonType;
        return this;
    }

    public RequestUtil<T> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;

    }

    public RequestUtil<T> setUserId(int userId) {
        this.uid = userId;
        return this;

    }

    public Observable<T> request() {

        LogUtil.print(bodyParameter);
        LogUtil.print(getRequestUrl()+url);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BaseInterceptor(headParameter))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(getRequestUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        requestInterface = retrofit.create(RequestInterface.class);

        Observable<T> observable = RxRequest.getRxHttp(this)
                .map(s -> {
                    return GsonUtil.fromJson(s, toJsonType);
                });

        observable = observable.map(e -> {
            if (e instanceof ApiResponse) {
                ApiResponse responseJson = (ApiResponse) e;
                LogUtil.print(responseJson.toJsonString());
            }
            return e;
        });
        return observable;

    }


    RequestInterface getRequestInterface() {
        return requestInterface;
    }

    String getRequestUrl(){
        return  baseUrl + headUrl;
    }

    String getSign(){
        //TODO 计算签名
        return "";
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getBodyParameter() {
        return bodyParameter;
    }

    public int getUid() {
        return uid;
    }

    public Map<String, String> getFileParameter() {
        return fileParameter;
    }
}
