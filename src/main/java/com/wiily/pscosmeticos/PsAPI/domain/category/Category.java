package com.wiily.pscosmeticos.PsAPI.domain.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.Normalizer;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    @NotBlank
    @Column(name = "category_name")
    String name;

    @Column(name = "category_slug")
    String slug;

    public Category(CategoryCreateRequest categoryDto) {
        name = categoryDto.name();
        slug = removeAccents(categoryDto.name().replace(' ', '-').toLowerCase());
    }

    private String removeAccents(String texto) {
        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");
    }

    public void edit(CategoryEditRequest editRequest) {
        if (editRequest.name() != null) {
            setName(editRequest.name());
            setSlug(removeAccents(editRequest.name().replace(' ', '-').toLowerCase()));
        }

    }
}
