package com.wiily.pscosmeticos.PsAPI.domain.category;

import jakarta.validation.constraints.NotNull;

public record CategoryEditRequest(
        @NotNull
        Long id,
        String name
) {
}
