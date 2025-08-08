package com.wiily.pscosmeticos.PsAPI.domain.product.dto.returns;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wiily.pscosmeticos.PsAPI.domain.product.Product;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReturnProductCreationData(Long id,
                                        String name,
                                        String slug,
                                        String tipo,
                                        String imageURL,
                                        String category,
                                        String sub_category,
                                        Map<String, String> cores,
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
                p.getType().toString(),
                p.getImage(),
                p.getCategory().getNome(),
                p.getSubCategory().getName(),
                p.getMultiColor(),
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
