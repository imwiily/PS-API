package com.wiily.pscosmeticos.PsAPI.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNome(String nome);
}
