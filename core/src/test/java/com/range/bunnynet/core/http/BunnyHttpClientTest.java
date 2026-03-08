package com.range.bunnynet.core.http;

import com.range.bunnynet.core.exception.BunnyConnectionFailedException;
import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BunnyHttpClientTest {

    private MockWebServer server;
    private BunnyHttpClient http;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        http = new BunnyHttpClient("test-api-key", client);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void upload_shouldSendCorrectHeadersAndBody() throws Exception {

        server.enqueue(new MockResponse().setResponseCode(201));

        byte[] data = "hello".getBytes(StandardCharsets.UTF_8);

        PutObjectRequest req = new PutObjectRequest(
                "file.txt",
                "text/plain",
                Map.of("Foo", "Bar"),
                new ByteArrayInputStream(data)
        );

        String url = server.url("/upload").toString();

        HttpRequest request = http.createPutRequest(
                url,
                req.contentType(),
                req.metadata(),
                req
        );

        int code = http.executeUpload(request);
        assertEquals(201, code);

        RecordedRequest recorded = server.takeRequest();

        assertEquals("PUT", recorded.getMethod());
        assertEquals("/upload", recorded.getPath());
        assertEquals("test-api-key", recorded.getHeader("AccessKey"));
        assertEquals("Bar", recorded.getHeader("Meta-Foo"));
        assertEquals("text/plain", recorded.getHeader("Content-Type"));
        assertArrayEquals(data, recorded.getBody().readByteArray());
    }

    @Test
    void upload_whenConnectionFails_shouldThrow() {

        PutObjectRequest req = new PutObjectRequest(
                "file.txt",
                "text/plain",
                null,
                new ByteArrayInputStream("x".getBytes(StandardCharsets.UTF_8))
        );

        HttpRequest request = http.createPutRequest(
                "http://127.0.0.1:1/upload",
                req.contentType(),
                req.metadata(),
                req
        );

        assertThrows(BunnyConnectionFailedException.class,
                () -> http.executeUpload(request));
    }

    @Test
    void download_shouldReturnMetadataAndStream() throws Exception {

        byte[] payload = "file-content".getBytes(StandardCharsets.UTF_8);

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "text/plain")
                .setBody(new String(payload))
                .addHeader("X-Test", "1")
        );

        String endpoint = server.url("/").toString().replaceAll("/$", "");

        try (GetObjectResponse res = http.downloadObject("zone1", endpoint, "a/b.txt")) {

            RecordedRequest recorded = server.takeRequest();

            assertEquals("GET", recorded.getMethod());
            assertEquals("/zone1/a/b.txt", recorded.getPath());
            assertEquals("test-api-key", recorded.getHeader("AccessKey"));

            assertEquals(200, res.getHttpStatus());
            assertEquals("text/plain", res.getContentType());

            assertEquals("1",
                    res.getHeaders().firstValue("X-Test").orElse(null));

            byte[] got = res.getStream().readAllBytes();
            assertArrayEquals(payload, got);

            assertEquals("https://zone1.b-cdn.net/a/b.txt", res.getPublicUrl());
        }
    }

    @Test
    void delete_shouldSendDeleteRequest() throws Exception {

        server.enqueue(new MockResponse().setResponseCode(204));

        String endpoint = server.url("/").toString().replaceAll("/$", "");

        int code = http.deleteObject("zone1", endpoint, "dead.txt");

        assertEquals(204, code);

        RecordedRequest recorded = server.takeRequest();

        assertEquals("DELETE", recorded.getMethod());
        assertEquals("/zone1/dead.txt", recorded.getPath());
        assertEquals("test-api-key", recorded.getHeader("AccessKey"));
    }
}