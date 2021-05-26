package org.catshake.photosharing.util;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

public class DelegatingNamedResource implements Resource {
    private final Resource resource;
    private final String filename;

    public DelegatingNamedResource(Resource resource, String filename) {
        Assert.notNull(resource, "resource == null");
        Assert.notNull(filename, "filename == null");

        this.resource = resource;
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public boolean exists() {
        return this.resource.exists();
    }

    @Override
    public boolean isReadable() {
        return this.resource.isReadable();
    }

    @Override
    public boolean isOpen() {
        return this.resource.isOpen();
    }

    @Override
    public boolean isFile() {
        return this.resource.isFile();
    }

    @Override
    public URL getURL() throws IOException {
        return this.resource.getURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.resource.getURI();
    }

    @Override
    public File getFile() throws IOException {
        return this.resource.getFile();
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return this.resource.readableChannel();
    }

    @Override
    public long contentLength() throws IOException {
        return this.resource.contentLength();
    }

    @Override
    public long lastModified() throws IOException {
        return this.resource.lastModified();
    }

    @Override
    public Resource createRelative(String s) throws IOException {
        return this.resource.createRelative(s);
    }

    @Override
    public String getDescription() {
        return this.resource.getDescription();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }
}
