package com.goit.feature;

import com.goit.feature.exceptions.NotFoundException;
import com.goit.feature.util.UtilHttpResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public class HttpStatusChecker {

    public String getStatusImage(int code) throws IOException, InterruptedException, NotFoundException {
        HttpResponse<String> response = new UtilHttpResponse().getStringHttpResponse(code);

        if(response.statusCode() != 404) {
            return response.uri().toString();
        } else {
            throw  new NotFoundException("Invalid code for request");
        }
    }
}
