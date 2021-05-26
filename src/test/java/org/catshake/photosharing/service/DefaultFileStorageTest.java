package org.catshake.photosharing.service;

import org.catshake.photosharing.service.filestorage.DefaultFileStorage;
import org.catshake.photosharing.service.filestorage.FileStorage;
import org.catshake.photosharing.util.DelegatingNamedResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.ByteArrayResource;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultFileStorageTest {
    @SuppressWarnings("PackageVisibleField")
    @TempDir
    Path temporaryDirectory;

    private FileStorage fileStorage;

    @BeforeEach
    void setUp() {
        fileStorage = new DefaultFileStorage(temporaryDirectory);
    }

    @Test
    void resourceSaved() {
        String filename = "foo";
        fileStorage.save(new DelegatingNamedResource(new ByteArrayResource(new byte[0]), filename));

        assertThat(fileStorage.findByName(filename))
                .isNotEmpty();
    }

    @Test
    void resourceSavedAndDeleted() {
        String filename = "bar";
        fileStorage.save(new DelegatingNamedResource(new ByteArrayResource(new byte[0]), filename));
        fileStorage.deleteByName(filename);

        assertThat(fileStorage.findByName(filename))
                .isEmpty();
    }
}