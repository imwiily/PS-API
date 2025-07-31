package com.wiily.pscosmeticos.PsAPI.controller;

import com.wiily.pscosmeticos.PsAPI.domain.product.CreateProductData;
import com.wiily.pscosmeticos.PsAPI.domain.product.ProductRepository;
import com.wiily.pscosmeticos.PsAPI.domain.product.ReturnProductCreationData;
import com.wiily.pscosmeticos.PsAPI.domain.returns.ApiResponse;
import com.wiily.pscosmeticos.PsAPI.service.ImageService;
import com.wiily.pscosmeticos.PsAPI.service.IngredientService;
import com.wiily.pscosmeticos.PsAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ImageService imageService;
    @Autowired
    ProductService service;
    @Autowired
    ProductRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<Object> createProduct(@RequestPart(name = "dados") CreateProductData data,
                                                @RequestPart(name = "imagem") MultipartFile image,
                                                UriComponentsBuilder uriBuilder) {
        var product = service.createProduct(data);
        if (product == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Category with id: '" + data.categoria() + "' do not exists!"));
        }
        var img = imageService.imageProcessor(image, product);
        product.setImage(img);
        repository.save(product);
        var uri = uriBuilder.path("/api/v1/products").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApiResponse(true, new ReturnProductCreationData(product)));
    }
}
