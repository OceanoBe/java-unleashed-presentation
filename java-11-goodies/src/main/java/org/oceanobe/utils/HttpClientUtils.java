package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UtilityClass
public class HttpClientUtils {

    /**
     * Supports HTTP/1.1 and HTTP/2
     */
    public static HttpResponse<String> send(HttpClient client, URI uri) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
