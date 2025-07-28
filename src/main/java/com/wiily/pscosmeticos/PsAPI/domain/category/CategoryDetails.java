package com.wiily.pscosmeticos.PsAPI.domain.category;

public record CategoryDetails(
        Long id,
        String name,
        String slug
) {
    public CategoryDetails(Category c) {
        this(c.getId(), c.getName(), c.getSlug());
    }
}
