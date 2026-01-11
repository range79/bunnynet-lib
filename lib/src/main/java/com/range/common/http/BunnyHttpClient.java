package com.range.common.http;

import com.range.common.dto.PutObjectRequest;
import com.range.common.exception.BunnyConnectionFailedException;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BunnyHttpClient {

    private final String apiKey;
    private final OkHttpClient client;

    public BunnyHttpClient(String apiKey, int connectionTimeout, int readTimeout) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public Request createPutRequest(String url, String contentType, Map<String, String> metadata, PutObjectRequest request) {
        RequestBody body = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse(contentType);
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                try (Source source = Okio.source(request.getInputStream())) {
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

    public int executeUpload(Request request) {
        try (Response response = client.newCall(request).execute()) {

            return response.code();
        } catch (IOException e) {
            throw new BunnyConnectionFailedException("Failed to execute upload to: " + request.url(), e);
        }
    }
}