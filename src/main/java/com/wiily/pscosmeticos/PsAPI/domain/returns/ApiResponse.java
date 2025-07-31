package com.wiily.pscosmeticos.PsAPI.domain.returns;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse(Boolean success, Object content) {}
