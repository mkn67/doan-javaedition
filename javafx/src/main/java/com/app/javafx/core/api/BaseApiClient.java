package com.app.javafx.core.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.app.javafx.core.security.SessionManager;

public class BaseApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";

    private final HttpClient client = HttpClient.newHttpClient();

    private HttpRequest.Builder builder(String endpoint) {
        HttpRequest.Builder req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json");

        String token = SessionManager.getInstance().getToken();

        if (token != null && !token.isEmpty()) {
            req.header("Authorization", "Bearer " + token);
        }

        return req;
    }

    public String get(String endpoint) throws IOException, InterruptedException {
        return send(builder(endpoint).GET().build());
    }

    public String post(String endpoint, String body) throws IOException, InterruptedException {
        return send(builder(endpoint)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build());
    }

    public String put(String endpoint, String body) throws IOException, InterruptedException {
        return send(builder(endpoint)
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build());
    }

    public String delete(String endpoint) throws IOException, InterruptedException {
        return send(builder(endpoint)
                .DELETE()
                .build());
    }

    private String send(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();

        if (status >= 200 && status < 300) {
            return response.body();
        } else if (status == 401) {
            throw new RuntimeException("Unauthorized - Token hết hạn hoặc sai");
        } else {
            throw new RuntimeException("API Error: " + status + " - " + response.body());
        }
    }
}