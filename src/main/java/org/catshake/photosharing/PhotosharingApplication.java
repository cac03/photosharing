package org.catshake.photosharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.Set;

@SpringBootApplication
@RestController
public class PhotosharingApplication {

    @RequestMapping("/hello")
    public String getHelloWorld() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(PhotosharingApplication.class, args);
    }

}
