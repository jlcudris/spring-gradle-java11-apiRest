package com.springj11gradle.platzimarker.domain.service;

import com.springj11gradle.platzimarker.domain.Product;
import com.springj11gradle.platzimarker.domain.Purchase;
import com.springj11gradle.platzimarker.domain.repository.PurchaseRepository;
import com.springj11gradle.platzimarker.persistence.entity.Compra;
import com.springj11gradle.platzimarker.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }
    public Optional<List<Purchase>> getByClient(String clientID) {
      return  purchaseRepository.getByClient(clientID);
    }

    public Purchase save(Purchase purchase) {

       return purchaseRepository.save(purchase);
    }
}
