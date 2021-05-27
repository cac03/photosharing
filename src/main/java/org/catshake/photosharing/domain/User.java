package org.catshake.photosharing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "passwordHash")
public class User {
    private Long id;
    private String username;
    private String passwordHash;
}
