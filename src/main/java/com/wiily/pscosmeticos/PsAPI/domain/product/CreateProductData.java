package com.wiily.pscosmeticos.PsAPI.domain.product;

import java.util.List;

public record CreateProductData(
        String nome,
        int categoria,
        double preco,
        double precoDesconto,
        String descricao,
        String descricaoCompleta,
        List<String> ingredientes,
        String modoUso,
        List<String> tags,
        boolean ativo
) {}
