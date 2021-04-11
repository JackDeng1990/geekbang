package client;

import okhttp3.*;

import java.io.IOException;

/**
 * @author admin
 */
public class MyOkHttpClient {

    private OkHttpClient client;
    public MyOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.build();
    }

    public String doGet(String url) {
        Response response = null;
        ResponseBody responseBody = null;
        String result;
        Request request = new Request.Builder().url(url).build();
        try {
            response = client.newCall(request).execute();
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
