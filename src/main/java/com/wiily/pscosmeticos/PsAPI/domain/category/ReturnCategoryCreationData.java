package com.wiily.pscosmeticos.PsAPI.domain.category;

public record ReturnCategoryCreationData(Long id,
                                         String nome,
                                         String slug,
                                         String descricao,
                                         String imagem,
                                         int totalprotudos,
                                         Boolean ativo) {
    public ReturnCategoryCreationData(Category category) {
        this(
                category.id,
                category.nome,
                category.slug,
                category.descricao,
                category.imageUrl,
                category.totalProdutos,
                category.ativo
        );
    }
}
