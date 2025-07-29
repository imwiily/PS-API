package com.wiily.pscosmeticos.PsAPI.controller;

import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/image/")
public class ImageController {
    @Autowired
    AppProperties properties;

    @GetMapping("{name}")
    public ResponseEntity<byte[]> viewImage(@PathVariable String name) throws IOException {
        File image = new File(properties.getStorage().getImageCategoryRoot() + name);
        System.out.println(image.toPath());
        if (!image.exists()) {
            return ResponseEntity.notFound().build();
        }
        byte[] content = Files.readAllBytes(image.toPath());

        return ResponseEntity.ok()
                .header("Content-Type", Files.probeContentType(image.toPath()))
                .header("Content-Disposition", "inline; filename=\"" + name + "\"")
                .body(content);
    }
}
