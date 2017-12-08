package com.vis;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import java.io.IOException;

public class SendRequest {
    private static SendRequest ourInstance = new SendRequest();

    public static SendRequest getInstance() {
        return ourInstance;
    }

    private SendRequest() {
    }

    public Content sendGetRequest(String url) {
        try {

            Content content = Request.Get("http://localhost:8080" + url)
                    .execute().returnContent();
            System.out.print("");

            return content;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public JSONObject sendPostRequest(String url, JSONObject jsonObject) {
        try {
            Content content = Request.Post("http://localhost:8080/" + url)
                    .bodyString(jsonObject.toString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent();
            System.out.print("");

            return new JSONObject(content.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public JSONObject sendPostRequest(String url, String string) {
        try {
            Content content = Request.Post("http://localhost:8080/" + url)
                    .bodyString(string, ContentType.APPLICATION_JSON)
                    .execute().returnContent();
            System.out.print("");

            return new JSONObject(content.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
