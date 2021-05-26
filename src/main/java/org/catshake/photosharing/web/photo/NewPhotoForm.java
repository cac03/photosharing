package org.catshake.photosharing.web.photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewPhotoForm {
    private MultipartFile photo;
    private String description;
}
