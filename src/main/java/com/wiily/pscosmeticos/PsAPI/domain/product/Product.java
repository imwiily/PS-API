package com.wiily.pscosmeticos.PsAPI.domain.product;

import com.wiily.pscosmeticos.PsAPI.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;

    @Column(name = "product_name")
    String name;

    @Column(name = "product_slug")
    String slug;

    @Column(name = "product_image")
    String image;

    @JoinColumn(name = "product_category")
    @ManyToOne
    Category category;

    @Column(name = "product_price")
    double price;

    @Column(name = "product_discount_price")
    double discountPrice;

    @Column(name = "product_description")
    String description;

    @Column(name = "product_complete_description")
    String completeDescription;

    @ManyToMany
    @JoinTable(
            name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    List<Ingredient> ingredientList;

    @Column(name = "product_how_to_use")
    String howToUse;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    List<Tag> tags;

    @Column(name = "product_active")
    boolean active;

    public Product(CreateProductData data) {
        name = data.nome();
        slug = data.nome().replace(" ", "-").toLowerCase();
        price = data.preco();
        discountPrice = data.precoDesconto();
        description = data.descricao();
        completeDescription = data.descricaoCompleta();
        howToUse = data.modoUso();
        active = data.ativo();
    }
}
