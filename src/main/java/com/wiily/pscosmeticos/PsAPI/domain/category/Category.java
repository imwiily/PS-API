package com.wiily.pscosmeticos.PsAPI.domain.category;

import com.wiily.pscosmeticos.PsAPI.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    @Column(name = "category_name")
    String nome;

    @Column(name = "category_slug")
    String slug;

    @Column(name = "category_description")
    String descricao;

    @Column(name = "category_image_url")
    String imageUrl;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    @Column(name = "category_total_products")
    int totalProdutos;

    @Column(name = "category_active")
    boolean ativo;

    public Category(@Valid CreateCategoryData categoryData) {

        nome = categoryData.nome();
        slug = createSlug(categoryData.nome());
        descricao = categoryData.descricao();
        totalProdutos = 0;
        ativo = categoryData.ativo();
    }

    private String createSlug(@NotBlank String nome) {
        return nome.toLowerCase().replace(" ", "-");
    }
}
