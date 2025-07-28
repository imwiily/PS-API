package com.wiily.pscosmeticos.PsAPI.controller;

import com.wiily.pscosmeticos.PsAPI.domain.category.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDetails> createCategory(@RequestBody @Valid CategoryCreateRequest categoryDto, UriComponentsBuilder uriBuilder) {
        var category = new Category(categoryDto);
        repository.save(category);

        var uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryDetails(category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetails> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(new CategoryDetails(repository.getReferenceById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryGetRequest>> getCategories(@PageableDefault(size = 6)Pageable page) {
        var pages = repository.findAll(page).map(CategoryGetRequest::new);
        return ResponseEntity.ok(pages);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CategoryDetails> editCategory(@RequestBody @Valid CategoryEditRequest editRequest) {
        var category = repository.getReferenceById(editRequest.id());
        category.edit(editRequest);
        repository.save(category);
        return ResponseEntity.ok(new CategoryDetails(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        var category = repository.getReferenceById(id);
        repository.delete(category);
        return ResponseEntity.noContent().build();
    }

}
