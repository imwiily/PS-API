package com.wiily.pscosmeticos.PsAPI.domain.category;

public record ReturnCategoryData(
        Long id,
        String nome,
        String slug,
        String descricao,
        String imageUrl,
        int totalProdutos,
        boolean ativo
) {
    public ReturnCategoryData(Category c) {
        this(
                c.id,
                c.nome,
                c.slug,
                c.descricao,
                c.imageUrl,
                c.totalProdutos,
                c.ativo
        );
    }
}
