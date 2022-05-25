package com.springj11gradle.platzimarker.persistence.crud;

import com.springj11gradle.platzimarker.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Long > {

    //recuperar toda la lista de producto que pertenezacn aun id categoria en especifico

    //de sta manera podemos realizar consulta directamente a la base de datos no es necesario seguir la combencion del camel case con esto
   // @Query(value = "SELECT * FROM productos WHERE id_categoria =?",nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(Long idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock,Boolean estado);
}
