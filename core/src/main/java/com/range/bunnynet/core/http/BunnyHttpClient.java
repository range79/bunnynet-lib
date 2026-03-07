package com.range.bunnynet.core.http;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.exception.BunnyConnectionFailedException;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class BunnyHttpClient {

    private final String apiKey;
    private final OkHttpClient client;
    public BunnyHttpClient(String apiKey, OkHttpClient client) {
        this.apiKey = apiKey;
        this.client = client;
    }

    public BunnyHttpClient(String apiKey, int connectionTimeout, int readTimeout) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }



    public Request createPutRequest(
            String url,
            MediaType contentType,
            Map<String, String> metadata,
            PutObjectRequest request
    ) {
        RequestBody body = new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                try (Source source = Okio.source(request.inputStream())) {
                    sink.writeAll(source);
                }
            }
        };

        Request.Builder builder = new Request.Builder()
                .url(url)
                .put(body)
                .addHeader("AccessKey", apiKey);

        if (metadata != null) {
            metadata.forEach((key, value) -> builder.addHeader("Meta-" + key, value));
        }

        return builder.build();
    }

    public int executeUpload(Request request) throws BunnyConnectionFailedException {
        try (Response response = client.newCall(request).execute()) {
            return response.code();
        } catch (IOException e) {
            throw new BunnyConnectionFailedException(
                    "Failed to execute upload to: " + request.url(), e
            );
        }
    }



    public GetObjectResponse downloadObject(
            String storageZone,
            String endpoint,
            String key
    ) throws BunnyConnectionFailedException {

        HttpUrl url = Objects.requireNonNull(HttpUrl.parse(endpoint))
                .newBuilder()
                .addPathSegment(storageZone)
                .addPathSegments(key)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("AccessKey", apiKey)
                .build();

        final Response response;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new BunnyConnectionFailedException(
                    "Failed to download from: " + url, e
            );
        }

        ResponseBody body = Objects.requireNonNull(response.body(), "Response body is null");

        MediaType mediaType = body.contentType();
        String contentType = mediaType != null
                ? mediaType.toString()
                : "application/octet-stream";

        return new GetObjectResponse(
                storageZone,
                key,
                "https://" + storageZone + ".b-cdn.net/" + key,
                contentType,
                body.contentLength(),
                response.headers(),
                response,
                body.byteStream(),
                response.code()
        );
    }

    public int deleteObject(String storageZone, String endpoint, String key) throws BunnyConnectionFailedException {

        HttpUrl url = Objects.requireNonNull(HttpUrl.parse(endpoint))
                .newBuilder()
                .addPathSegment(storageZone)
                .addPathSegments(key)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader("AccessKey", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.code();
        } catch (IOException e) {
            throw new BunnyConnectionFailedException(
                    "Failed to delete object at: " + url, e
            );
        }
    }
}