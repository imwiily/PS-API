package com.wiily.pscosmeticos.PsAPI.domain.product.dto.returns;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import com.wiily.pscosmeticos.PsAPI.domain.product.ingredient.Ingredient;
import com.wiily.pscosmeticos.PsAPI.domain.product.Product;
import com.wiily.pscosmeticos.PsAPI.domain.product.tag.Tag;

import java.time.LocalDateTime;
import java.util.List;

public record ReturnProductGetter(Long id,
                                  String name,
                                  String slug,
                                  String imageURL,
                                  CategoryRecord category,
                                  double price,
                                  double discountPrice,
                                  String description,
                                  String completeDescription,
                                  List<String> ingredients,
                                  String howToUse,
                                  List<String> tags,
                                  boolean active,
                                  LocalDateTime createAt,
                                  LocalDateTime updateAt) {
    public ReturnProductGetter(Product p) {
        this(
                p.getId(),
                p.getName(),
                p.getSlug(),
                p.getImage(),
                new CategoryRecord(p.getCategory()),
                p.getPrice(),
                p.getDiscountPrice(),
                p.getDescription(),
                p.getCompleteDescription(),
                p.getIngredientList().stream().map(Ingredient::getIngredient).toList(),
                p.getHowToUse(),
                p.getTags().stream().map(Tag::getName).toList(),
                p.getActive(),
                p.getCreatedTime(),
                p.getUpdateTime()
        );
    }
}
record CategoryRecord(long id,
                      String name) {
     CategoryRecord(Category c) {
        this(
                c.getId(),
                c.getNome());
    }
}

