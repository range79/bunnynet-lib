package com.range.common.http;


import com.range.common.dto.PutObjectRequest;
import com.range.common.exception.BunnyConnectionFailedException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class BunnyHttpClient {

    private final String apiKey;
    private final int connectionTimeout;
    private final int readTimeout;

    public BunnyHttpClient(String apiKey, int connectionTimeout, int readTimeout) {
        this.apiKey = apiKey;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    public HttpURLConnection createPutConnection(String url, String contentType, Map<String, String> metadata) {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("AccessKey", apiKey);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);
            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);

            if (metadata != null) {
                for (Map.Entry<String, String> entry : metadata.entrySet()) {
                    connection.setRequestProperty("Meta-" + entry.getKey(), entry.getValue());
                }
            }

            return connection;

        } catch (IOException e) {
            throw new BunnyConnectionFailedException("Failed to open connection to URL: " + url, e);
        }
    }

    public void uploadData(HttpURLConnection connection, PutObjectRequest request) {
        try (var input = request.getInputStream();
             var out = connection.getOutputStream()) {
            input.transferTo(out);
        } catch (IOException e) {
            throw new BunnyConnectionFailedException("Failed to write data to connection", e);
        }
    }

    public int getResponseCode(HttpURLConnection connection) {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            throw new BunnyConnectionFailedException("Failed to get response code", e);
        }
    }
}

