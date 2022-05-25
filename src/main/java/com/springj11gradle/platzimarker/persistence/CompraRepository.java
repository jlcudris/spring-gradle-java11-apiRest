package com.springj11gradle.platzimarker.persistence;

import com.springj11gradle.platzimarker.domain.Purchase;
import com.springj11gradle.platzimarker.domain.repository.PurchaseRepository;
import com.springj11gradle.platzimarker.persistence.crud.CompraCrudRepository;
import com.springj11gradle.platzimarker.persistence.entity.Compra;
import com.springj11gradle.platzimarker.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras =(List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {

    Compra compra = purchaseMapper.toCompra(purchase);
    compra.getProductos().forEach(producto -> producto.setCompra(compra));
    return  purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
