package org.catshake.photosharing.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    User saveUser(User user);
}
