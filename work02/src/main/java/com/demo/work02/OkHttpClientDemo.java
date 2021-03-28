package com.demo.work02;

import okhttp3.*;

import java.io.IOException;

/**
 * work02作业，使用 HttpClient 或 OkHttp 访问 http://localhost:8801
 * @author dengchao
 */
public class OkHttpClientDemo {

    public static void main(String[] args) throws IOException {
        String url="http://localhost:8081";
        String request = OkHttpClient(url);
        if (request != null) {
            System.out.println(request);
        }
    }

    private static String OkHttpClient(String url) {
        Response response = null;
        ResponseBody responseBody = null;
        String result;
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder().url(httpUrlBuilder.build()).build();
        try {
            response = okHttpClient.newCall(request).execute();
            responseBody = response.body();
            result = responseBody.string();
        } catch (IOException e) {
            System.out.println("OkHttpClient发送请求失败，error:" +  e.getMessage());
            return null;
        } finally {
            if (null != responseBody) {
                responseBody.close();
            }
            if (null != response) {
                response.close();
            }
        }
        return result;
    }
}
