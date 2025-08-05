package com.wiily.pscosmeticos.PsAPI.domain.category;

import jakarta.validation.constraints.NotBlank;

public record EditCategoryData(
        @NotBlank
        Long id,
        String nome,
        String descricao,
        Boolean ativo
) {
}
