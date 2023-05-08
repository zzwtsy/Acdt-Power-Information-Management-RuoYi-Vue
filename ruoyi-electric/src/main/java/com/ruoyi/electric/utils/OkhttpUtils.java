package com.ruoyi.electric.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * okhttp 工具类
 *
 * @author zzwtsy
 * @since 2023/04/19
 */
public class OkhttpUtils {
    private static final OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    public static Response get(String url, Headers headers) throws IOException {
        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);

        if (headers != null) {
            builder.headers(headers);
        }

        Request request = builder.build();
        return client.newCall(request).execute();
    }

    public static Response post(String url, Headers headers, RequestBody requestBody) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(url);

        if (headers != null) {
            builder.headers(headers);
        }

        if (requestBody != null) {
            builder.post(requestBody);
        }

        Request request = builder.build();
        return client.newCall(request).execute();
    }
}
