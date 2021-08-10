package edu.mygdx.controller;

import java.io.IOException;
import java.net.ConnectException;

import edu.mygdx.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

public class RegController {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static User login(String username, String password) {
        String url = "http://localhost:8081/client/login";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return sendPost(url, jsonObject);
    }

    public static void updateScore(Long id, Long score) {
        String url = "http://localhost:8081/client/score";
        JSONObject jsonObject = new JSONObject();
        System.out.println(id);
        System.out.println(score);
        jsonObject.put("id", id);
        jsonObject.put("score", score);
        sendPost(url, jsonObject);
    }

    public static User reg(String username, String password, boolean isPremium) {
        String url = "http://localhost:8081/client/reg";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        if(isPremium) jsonObject.put("role", 1);
        else jsonObject.put("role", 0);
        return sendPost(url, jsonObject);
    }

    public static User sendPost(String url, JSONObject jsonObject) {
        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(s);
        httppost.setHeader("Content-type", "application/json");
        httppost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            return null;
        }
        HttpEntity entity = response.getEntity();
        String data = null;
        try {
            data = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        User user = JSONObject.parseObject(data, User.class);
        System.out.println(user.getUid());
        System.out.println(user.getUsername());
        System.out.println(user.getScore());
        return user;
    }
}
