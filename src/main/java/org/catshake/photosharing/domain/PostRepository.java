package org.catshake.photosharing.domain;

import java.util.List;

public interface PostRepository {
    Post savePost(Post post);

    List<Post> findAll();
}
