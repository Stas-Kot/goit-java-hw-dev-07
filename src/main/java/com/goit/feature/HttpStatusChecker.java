package com.goit.feature;

import com.goit.feature.exceptions.NotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public String getStatusImage(int code) throws IOException, InterruptedException, NotFoundException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getUrlString(code)))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 404) {
            return response.uri().toString();
        } else {
            throw new NotFoundException("There is not image for HTTP status " + code + "!");
        }
    }

    private String getUrlString(int code) {
        return "https://http.cat/" + code + ".jpg";
    }
}
