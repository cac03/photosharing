package org.catshake.photosharing.service.filestorage;

import org.springframework.core.io.Resource;

import java.util.Optional;

public interface FileStorage {
    void save(Resource resource);

    Optional<Resource> findByName(String name);

    void deleteByName(String name);
}
