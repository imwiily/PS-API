package com.wiily.pscosmeticos.PsAPI.service;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.category.CategoryRepository;
import com.wiily.pscosmeticos.PsAPI.domain.category.EditCategoryData;
import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BiConsumer;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    @Autowired
    AppProperties properties;
    @Autowired
    ImageService imageService;

    public void editCategory(@Valid EditCategoryData cd, MultipartFile image) {
        var c = repository.getReferenceById(cd.id());
        edit(cd.nome(), c, Category::setNome);
        edit(cd.descricao(), c, Category::setDescricao);
        edit(cd.ativo(), c, Category::setAtivo);
        if (image != null) {
            editImage(c, image);
        }
        repository.save(c);
    }
    private void editImage(Category category, MultipartFile image) {
        try {
            String path = properties.getStorage().getImageCategoryRoot() + imageService.getImagePath(category);
            Files.delete(Path.of(path));
            category.setImageUrl(imageService.imageProcessor(image, category));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T, V> void edit(V value, T obj, BiConsumer<T, V> setter) {
        if (value != null) {
            setter.accept(obj, value);
        }
    }
}
