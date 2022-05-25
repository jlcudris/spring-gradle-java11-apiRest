package com.springj11gradle.platzimarker.domain.service;

import com.springj11gradle.platzimarker.domain.Product;
import com.springj11gradle.platzimarker.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    //inyecta el product repository

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<Product>getProductId(long productId){
        return productRepository.getProductId(productId);
    }

    public Optional<List<Product>> getProductByCategoria(long categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
    public boolean delete(long producId){
        return getProductId(producId).map(product -> {
            productRepository.delete(producId);
            return true;
        }).orElse(false);

    }

    public Product update(long id, Product product){
        return productRepository.update(id,product);

    }


}
