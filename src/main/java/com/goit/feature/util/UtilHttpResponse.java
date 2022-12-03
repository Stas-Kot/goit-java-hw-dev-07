package com.goit.feature.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UtilHttpResponse {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    public HttpResponse<String> getStringHttpResponse(int code) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getUrlString(code)))
                .GET()
                .build();

        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String getUrlString(int code) {
        return "https://http.cat/" + code + ".jpg";
    }
}
