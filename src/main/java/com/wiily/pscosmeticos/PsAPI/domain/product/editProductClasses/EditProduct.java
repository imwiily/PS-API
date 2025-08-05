package com.wiily.pscosmeticos.PsAPI.domain.product.editProductClasses;

import com.wiily.pscosmeticos.PsAPI.domain.product.CreateProductData;
import com.wiily.pscosmeticos.PsAPI.domain.product.Product;

public interface EditProduct {
    void edit(Product p, CreateProductData data);
}
