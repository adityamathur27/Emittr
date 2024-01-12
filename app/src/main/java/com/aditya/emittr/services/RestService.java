package com.aditya.emittr.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestService {

    String URL = "http://192.168.29.196:8080";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public Response post(String jsonInputString, String path) {
        OkHttpClient client = new OkHttpClient();
        try{
        RequestBody body = RequestBody.create(jsonInputString, JSON);
        String url = URL.concat(path);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public Response get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
    public Response get(Map<String, String> parameters, String path) {
        OkHttpClient client = new OkHttpClient();
        try{
            String url = URL.concat(path);

            HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
            for(Map.Entry<String, String> param : parameters.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
            url = httpBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public Response postCheckAnswer23(int languageId, int questionId, String selectedOption) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("languageId", languageId);
        jsonObject.put("questionId", questionId);
        jsonObject.put("selectedOption", selectedOption);
        String apiUrl = "http://localhost:8080/api/questions/check-answer";
        String urlWithParams = apiUrl + "?languageId=" + languageId + "&questionId=" + questionId + "&selectedOption=" + selectedOption;
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        String url = URL + "/api/checkAnswer/languageId/" + languageId + "/questionId/" + questionId + "/selectedOption/" + selectedOption + "/";
        Request request = new Request.Builder()
                .url(urlWithParams)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

}