package com.range.common.dto;

import java.io.File;
import java.io.InputStream;

public abstract class RequestBody {

    public static RequestBody fromFile(File file) {
        return new FromFile(file);
    }

    public static RequestBody fromInputStream(InputStream inputStream, long size) {
        return new FromInputStream(inputStream, size);
    }

    public static RequestBody fromBytes(byte[] bytes) {
        return new FromBytes(bytes);
    }

    public static final class FromFile extends RequestBody {
        private final File file;
        private FromFile(File file) { this.file = file; }
        public File getFile() { return file; }
    }

    public static final class FromInputStream extends RequestBody {
        private final InputStream inputStream;
        private final long size;
        private FromInputStream(InputStream inputStream, long size) {
            this.inputStream = inputStream;
            this.size = size;
        }
        public InputStream getInputStream() { return inputStream; }
        public long getSize() { return size; }
    }

    public static final class FromBytes extends RequestBody {
        private final byte[] bytes;
        private FromBytes(byte[] bytes) { this.bytes = bytes; }
        public byte[] getBytes() { return bytes; }
    }
}