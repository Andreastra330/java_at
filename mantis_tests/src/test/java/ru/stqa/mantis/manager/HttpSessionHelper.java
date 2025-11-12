package ru.stqa.mantis.manager;


import okhttp3.*;
import ru.stqa.mantis.config.ConfigReader;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase{


    OkHttpClient client;
    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient().newBuilder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }


    public void login(String login, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username",login)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(ConfigReader.buildUrl("/login.php"))
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(ConfigReader.getBaseUrl())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
