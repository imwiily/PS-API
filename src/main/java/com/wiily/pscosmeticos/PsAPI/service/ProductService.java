package com.wiily.pscosmeticos.PsAPI.service;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.category.CategoryRepository;
import com.wiily.pscosmeticos.PsAPI.domain.product.*;
import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

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

    public Product editProduct(CreateProductData data, Product p) {
        if (data.nome() != null ) {
            p.setName(data.nome());
            p.setSlug(data.nome().replace(" ", "-").toLowerCase());
        }
        if (data.categoria() != 0){
            var category = categoryRepository.getReferenceById((long) data.categoria());
            p.setCategory(category);
        }
        if (data.preco() != 0.0f) { p.setPrice(data.preco());}
        if (data.precoDesconto() != 0.0f) { p.setDiscountPrice(data.precoDesconto());}
        if (data.descricao() != null) { p.setDescription(data.descricao()); }
        if (data.descricaoCompleta() != null) {p.setCompleteDescription(data.descricaoCompleta()); }
        if (!data.ingredientes().isEmpty()) {
            p.setIngredientList(ingredientService.createIngredients(data.ingredientes()));
        }
        if (data.modoUso() != null) { p.setHowToUse(data.modoUso());}
        if (!data.tags().isEmpty()) { p.setTags(tagService.createTags(data.tags()));}
        if (data.ativo() != p.getActive()) { p.setActive(data.ativo()); }
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
