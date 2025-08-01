package com.wiily.pscosmeticos.PsAPI.service;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.product.Product;
import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    AppProperties properties;

    // Saves and return the image URL.
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String imageProcessor(MultipartFile image, Object object) {
        var objectInfo = objectType(object);
        var fileLocation = objectInfo.getLast();
        try {
            String imageName = objectInfo.getFirst() + "-" + UUID.randomUUID() + Objects.requireNonNull(image.getContentType()).replace("image/", ".");
            File imageFolder = new File(fileLocation);
            if (!imageFolder.exists()) imageFolder.mkdirs();
            image.transferTo(new File(fileLocation, imageName));
            return properties.getApi().getDomainIp() + "/api/v1/image/" + imageName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> objectType(Object object) {
        List<String> list = new ArrayList<>();
        switch (object) {
            case Category c -> {
                list.add(c.getSlug());
                list.add(properties.getStorage().getImageCategoryRoot());
            }
            case Product p -> {
                list.add(p.getSlug());
                list.add(properties.getStorage().getImageProductRoot());
            }
            default -> throw new IllegalStateException("Unexpected value: " + object);
        }
        return list;
    }

    public String getImagePath(Category category) {
        String[] url = category.getImageUrl().split("/");
        return url[4];
    }
    public String getImagePath(Product product) {
        String[] url = product.getImage().split("/");
        return url[4];
    }
}
