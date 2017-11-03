package util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class HttpUtil<T> {

    private static final long CONNECT_TIME_OUT = 15 * 1000;
    private static final long READ_TIME_OUT = 20 * 1000;

    private static FormBody.Builder bodyBuilder = new FormBody.Builder();
    private String url;
    private Type type;


    public static <T> HttpUtil<T> builder() {
        HttpUtil<T> httpUtil = new HttpUtil<T>();
        return httpUtil;
    }

    public HttpUtil<T> addBody(String key, String value) {
        bodyBuilder.add(key, value);
        return this;
    }

    public HttpUtil<T> url(String url) {
        this.url = url;
        return this;
    }

    public HttpUtil<T> type(Type type) {
        this.type = type;
        return this;
    }


    public Observable<String> post() {

        return Observable.create(subscriber -> {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS);

            OkHttpClient client = builder.build();
            String result = null;
            InputStream is = null;
            ByteArrayOutputStream bos = null;
            Call call = null;
            try {
                okhttp3.Request request;

                RequestBody requestBody = bodyBuilder.build();
                request = new okhttp3.Request.Builder().url(url)
                        .post(requestBody).build();
                final Call call1 = client.newCall(request);
                call = call1;

                Response response = call.execute();
                if (response.code() == 200) {
                    is = response.body().byteStream();
                    if (is != null) {
                        bos = new ByteArrayOutputStream();
                        byte b[] = new byte[1024];
                        int i = 0;
                        while ((i = is.read(b, 0, b.length)) != -1) {
                            bos.write(b, 0, i);
                        }
                        result = new String(bos.toByteArray(), "UTF-8");
                    } else {
                        result = null;
                    }
                } else {
                    LogUtil.print("error code:" + response.code() + " url:" + url);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (call != null) {
                        call.cancel();
                    }
                    if (is != null) {
                        is.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                }
            }
            LogUtil.print(result);
            //subscriber.onNext(GsonUtil.fromJson(result, type));
            subscriber.onNext(result);
            subscriber.onComplete();
        });

    }

}
