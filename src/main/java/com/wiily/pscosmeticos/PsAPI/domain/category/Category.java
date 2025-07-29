package com.wiily.pscosmeticos.PsAPI.domain.category;

import com.wiily.pscosmeticos.PsAPI.infra.config.AppProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
