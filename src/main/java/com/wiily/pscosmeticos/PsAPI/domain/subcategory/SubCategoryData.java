package com.wiily.pscosmeticos.PsAPI.domain.subcategory;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;

public record SubCategoryData(
        String name,
        CategoryInfo category
) {
    public SubCategoryData(SubCategory sc) {
        this(
                sc.getName(),
                new CategoryInfo(sc.getCategory())
        );
    }
}

record CategoryInfo(
        Long id,
        String name
) {
    public CategoryInfo(Category c) {
        this(
                c.getId(),
                c.getNome()
        );
    }
}
