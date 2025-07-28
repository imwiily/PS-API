package com.wiily.pscosmeticos.PsAPI.domain.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateRequest(
        @NotBlank
        String name
) {

}
