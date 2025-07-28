package com.wiily.pscosmeticos.PsAPI.domain.category;

public record CategoryGetRequest(
        Long id,
        String name,
        String slug
) {
    public CategoryGetRequest(Category c) {
        this(c.id, c.name, c.slug);
    }
}
