package com.range.bunnynet.core.http;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.exception.BunnyConnectionFailedException;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;


public final class BunnyHttpClient {

    private final String apiKey;
    private final HttpClient client;

    public BunnyHttpClient(String apiKey, HttpClient client) {
        this.apiKey = apiKey;
        this.client = client;
    }

    public BunnyHttpClient(String apiKey, int connectionTimeout) {
        this.apiKey = apiKey;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectionTimeout))
                .build();
    }

    public HttpRequest createPutRequest(
            String url,
            String contentType,
            Map<String, String> metadata,
            PutObjectRequest request
    ) {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("AccessKey", apiKey)
                .header("Content-Type", contentType)
                .PUT(HttpRequest.BodyPublishers.ofInputStream(request::inputStream));

        if (metadata != null) {
            metadata.forEach((key, value) ->
                    builder.header("Meta-" + key, value)
            );
        }

        return builder.build();
    }

    public int executeUpload(HttpRequest request)
            throws BunnyConnectionFailedException {

        try {
            HttpResponse<Void> response =
                    client.send(request, HttpResponse.BodyHandlers.discarding());

            return response.statusCode();

        } catch (Exception e) {
            throw new BunnyConnectionFailedException(
                    "Failed to execute upload to: " + request.uri(), e
            );
        }
    }

    public GetObjectResponse downloadObject(
            String storageZone,
            String endpoint,
            String key
    ) throws BunnyConnectionFailedException {

        String url = endpoint + "/" + storageZone + "/" + key;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("AccessKey", apiKey)
                .build();

        try {

            HttpResponse<InputStream> response =
                    client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            InputStream body = response.body();

            String contentType = response.headers()
                    .firstValue("Content-Type")
                    .orElse("application/octet-stream");

            long contentLength = response.headers()
                    .firstValue("Content-Length")
                    .map(Long::parseLong)
                    .orElse(-1L);

            return new GetObjectResponse(
                    storageZone,
                    key,
                    "https://" + storageZone + ".b-cdn.net/" + key,
                    contentType,
                    contentLength,
                    response.headers(),
                    body,
                    response.statusCode()
            );

        } catch (Exception e) {
            throw new BunnyConnectionFailedException(
                    "Failed to download from: " + url, e
            );
        }
    }

    public int deleteObject(
            String storageZone,
            String endpoint,
            String key
    ) throws BunnyConnectionFailedException {

        String url = endpoint + "/" + storageZone + "/" + key;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .header("AccessKey", apiKey)
                .build();

        try {

            HttpResponse<Void> response =
                    client.send(request, HttpResponse.BodyHandlers.discarding());

            return response.statusCode();

        } catch (Exception e) {
            throw new BunnyConnectionFailedException(
                    "Failed to delete object at: " + url, e
            );
        }
    }
}