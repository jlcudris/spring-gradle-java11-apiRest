package com.springj11gradle.platzimarker.persistence.mapper;

import com.springj11gradle.platzimarker.domain.Purchase;
import com.springj11gradle.platzimarker.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    //mapeo para una compra en especifico
    @Mappings({
            @Mapping(source ="idCompra",target = "purchaseId"),
            @Mapping(source ="idCliente",target = "clientId"),
            @Mapping(source ="fecha",target = "date"),
            @Mapping(source ="medioPago",target = "paymentMethod"),
            @Mapping(source ="comentario",target = "comment"),
            @Mapping(source ="estado",target = "state"),
            @Mapping(source ="productos",target = "item"),
    })
    Purchase toPurchase(Compra compra);
//mapeo para varias compras ademas de que no hacemos mas nada por que ya toma la configuracion en singular
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente",ignore = true)
    Compra toCompra(Purchase purchase);
}
