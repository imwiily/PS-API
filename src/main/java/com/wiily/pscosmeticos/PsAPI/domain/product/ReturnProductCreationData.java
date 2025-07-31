package com.wiily.pscosmeticos.PsAPI.domain.product;

import java.util.List;

public record ReturnProductCreationData(Long id,
                                        String name,
                                        String slug,
                                        String imageURL,
                                        String category,
                                        double price,
                                        double discountPrice,
                                        String description,
                                        String completeDescription,
                                        List<String> ingredients,
                                        String howToUse,
                                        List<String> tags,
                                        boolean active) {
    public ReturnProductCreationData(Product p) {
        this(
                p.id,
                p.name,
                p.slug,
                p.image,
                p.category.getNome(),
                p.price,
                p.discountPrice,
                p.description,
                p.completeDescription,
                p.getIngredientList().stream().map(Object::toString).toList(),
                p.howToUse,
                p.tags.stream().map(Object::toString).toList(),
                p.active
        );
    }
}
