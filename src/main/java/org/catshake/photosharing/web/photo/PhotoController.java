package org.catshake.photosharing.web.photo;

import lombok.AllArgsConstructor;
import org.catshake.photosharing.service.filestorage.FileStorage;
import org.catshake.photosharing.web.security.PhotoSharingUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
@AllArgsConstructor
public class PhotoController {
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private final FileStorage fileStorage;

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getPhotoById(@PathVariable("name") String name) {
        return fileStorage.findByName(name)
                .map(this::toOkResponse)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<Resource> toOkResponse(Resource resource) {
        Assert.notNull(resource, "resource == null");

        MediaType mediaType = MediaTypeFactory.getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers -> headers.setContentType(mediaType))
                .body(resource);
    }

    @GetMapping
    public String getNewPhotoForm(Model model) {
        model.addAttribute("newPhotoForm", new NewPhotoForm());
        return "upload";
    }

    @PostMapping
    public String postPhoto(
            @AuthenticationPrincipal PhotoSharingUserDetails userDetails,
            NewPhotoForm newPhotoForm) {
        throw new RuntimeException("todo");
        // return "redirect:/explore";
    }
}
