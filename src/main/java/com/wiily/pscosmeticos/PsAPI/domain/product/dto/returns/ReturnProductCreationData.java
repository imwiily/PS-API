package com.wiily.pscosmeticos.PsAPI.domain.product.dto.returns;

import com.wiily.pscosmeticos.PsAPI.domain.product.Product;

import java.util.List;

public record ReturnProductCreationData(Long id,
                                        String name,
                                        String slug,
                                        String imageURL,
                                        String category,
                                        String sub_category,
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
                p.getId(),
                p.getName(),
                p.getSlug(),
                p.getImage(),
                p.getCategory().getNome(),
                p.getSubCategory().getName(),
                p.getPrice(),
                p.getDiscountPrice(),
                p.getDescription(),
                p.getCompleteDescription(),
                p.getIngredientList().stream().map(Object::toString).toList(),
                p.getHowToUse(),
                p.getTags().stream().map(Object::toString).toList(),
                p.getActive()
        );
    }
}
