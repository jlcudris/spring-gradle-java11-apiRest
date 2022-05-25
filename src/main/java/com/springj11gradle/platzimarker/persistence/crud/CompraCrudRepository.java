package com.springj11gradle.platzimarker.persistence.crud;

import com.springj11gradle.platzimarker.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Long> {
    Optional<List<Compra>> findByIdCliente(String idcliente);
}
