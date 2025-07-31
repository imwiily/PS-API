package com.wiily.pscosmeticos.PsAPI.service;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.category.CategoryRepository;
import com.wiily.pscosmeticos.PsAPI.domain.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IngredientService ingredientService;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryRepository categoryRepository;

    public Product createProduct(CreateProductData data) {
        var product = new Product(data);
        Optional<Category> category = categoryRepository.findById((long) data.categoria());
        if (category.isEmpty()) {
            return null;
        }
        product.setIngredientList(ingredientService.createIngredients(data.ingredientes()));
        product.setTags(tagService.createTags(data.tags()));
        product.setCategory(category.get());
        return product;
    }
}
