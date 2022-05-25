package com.springj11gradle.platzimarker.domain.repository;

import com.springj11gradle.platzimarker.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(Long  categoryId);
    Optional<List<Product>> getScaseProducts(int quantity);
    Optional<Product> getProductId(long productId);
    Product save(Product product);
    void delete(long productId);

    Product update( long id,Product product);


}
