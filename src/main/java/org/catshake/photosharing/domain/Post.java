package org.catshake.photosharing.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class Post {
    private Long id;
    private Long userId;
    private String filename;
    private String description;
    private Instant date;
}
