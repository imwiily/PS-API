package com.wiily.pscosmeticos.PsAPI.service;

import com.wiily.pscosmeticos.PsAPI.domain.category.CategoryRepository;
import com.wiily.pscosmeticos.PsAPI.infra.exception.exceptions.CategoryNotExist;
import com.wiily.pscosmeticos.PsAPI.domain.product.*;
import com.wiily.pscosmeticos.PsAPI.domain.product.editProductClasses.EditProduct;
import com.wiily.pscosmeticos.PsAPI.domain.product.dto.datas.CreateProductData;
import com.wiily.pscosmeticos.PsAPI.domain.subcategory.SubCategoryRepository;
import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    IngredientService ingredientService;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository repository;
    @Autowired
    AppProperties properties;
    @Autowired
    ImageService imageService;
    @Autowired
    List<EditProduct> edit;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    public Product createProduct(CreateProductData data, MultipartFile image) {
        if (!categoryRepository.existsById((long) data.categoria())) {
            throw new CategoryNotExist("The category id '" + data.categoria() + "' do not exist!");
        }
        var category = categoryRepository.getReferenceById((long) data.categoria());
        var sub_category = subCategoryRepository.subCategoryFindByCategory(category, data.sub_categoria());
        if (sub_category.isEmpty()) throw new RuntimeException("Sub categoria nao percente a categoria!");
        var ingredients = ingredientService.createIngredients(data.ingredientes());
        var tags = tagService.createTags(data.tags());
        var product = new Product(data, category, ingredients, tags, sub_category.get());
        var img = imageService.imageProcessor(image, product);
        product.setImage(img);
        return product;
    }

    public Product editProduct(CreateProductData data, Product p) {
        edit.forEach(ep -> ep.edit(p, data));
        p.setUpdateTime(LocalDateTime.now(p.getZone()));
        repository.save(p);
        return p;
    }

    public Product editImage(Product product, MultipartFile image) {
        try {
            String path = properties.getStorage().getImageProductRoot() + imageService.getImagePath(product);
            Files.delete(Path.of(path));
            product.setImage(imageService.imageProcessor(image, product));
            return product;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
