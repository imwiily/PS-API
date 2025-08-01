package com.wiily.pscosmeticos.PsAPI.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryNomeIgnoreCase(String categoryName, Pageable pageable);
}
