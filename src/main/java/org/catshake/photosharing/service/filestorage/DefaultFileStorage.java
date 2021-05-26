package org.catshake.photosharing.service.filestorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class DefaultFileStorage implements FileStorage {
    private static final Logger logger = LoggerFactory.getLogger(DefaultFileStorage.class);

    private final Path directory;

    public DefaultFileStorage(@Value("photos") Path directory) {
        Assert.notNull(directory, "directory == null");
        createIfNotExists(directory);
        Assert.isTrue(Files.isDirectory(directory), () -> directory + " must be directory");

        this.directory = directory;
    }

    private static void createIfNotExists(Path directory) {
        if (Files.exists(directory)) {
            return;
        }
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            throw new FileStorageException("Unable to create directory = " + directory, e);
        }
    }

    @Override
    public void save(Resource resource) {
        Assert.notNull(resource, "resource == null");
        Assert.isTrue(resource.getFilename() != null,
                () -> "resource = " + resource + " must have filename");

        PathResource pathResource = createPersistentPathResource(resource.getFilename());
        Assert.state(!pathResource.exists(), "file with name = " + resource.getFilename() + " already exists");
        copyResource(resource, pathResource);
        logger.info("Saved resource = {}", resource);
    }

    private static void copyResource(Resource resource, PathResource pathResource) {
        try (OutputStream outputStream = pathResource.getOutputStream();
             InputStream inputStream = resource.getInputStream()) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new FileStorageException("Cannot save " + resource + " to " + pathResource);
        }
    }

    @Override
    public Optional<Resource> findByName(String name) {
        Assert.notNull(name, "name == null");

        PathResource pathResource = createPersistentPathResource(name);
        if (pathResource.exists()) {
            return Optional.of(pathResource);
        }
        return Optional.empty();
    }

    @Override
    public void deleteByName(String name) {
        try {
            Files.delete(getPathForName(name));
        } catch (IOException e) {
            throw new FileStorageException("Unable to delete file by name = " + name, e);
        }
    }

    private Path getPathForName(String name) {
        Assert.notNull(name, "name == null");
        return directory.resolve(name);
    }

    private PathResource createPersistentPathResource(String name) {
        Assert.notNull(name, "name == null");
        return new PathResource(getPathForName(name));
    }
}
