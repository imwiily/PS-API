package com.wiily.pscosmeticos.PsAPI.controller;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.subcategory.SubCategory;

public record ReturnSubCategoryData(
        Long id,
        String name,
        CategoryInfo category_info
) {
    public ReturnSubCategoryData(SubCategory s) {
        this(
                s.getId(),
                s.getName(),
                new CategoryInfo(s.getCategory())
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
