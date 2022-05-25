package com.springj11gradle.platzimarker.persistence;

import com.springj11gradle.platzimarker.domain.Product;
import com.springj11gradle.platzimarker.domain.repository.ProductRepository;
import com.springj11gradle.platzimarker.persistence.crud.ProductoCrudRepository;
import com.springj11gradle.platzimarker.persistence.entity.Producto;
import com.springj11gradle.platzimarker.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos =(List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);

    }

    @Override
    public Optional<List<Product>> getByCategory(Long categoryId) {

        List<Producto> productos =productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);

        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScaseProducts(int quantity) {
       Optional<List<Producto>> productos =productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
       //no se tiene un mapaeador que combierta a una lista de opcionales hay que hacerle a los productos un map
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProductId(long productId) {

        return productoCrudRepository.findById(productId).map(prod-> productMapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto producto =productMapper.toProducto(product);

         return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    public void delete(long idProducto){
        productoCrudRepository.deleteById(idProducto);
    }


    @Override
    public Product update(long productId,Product product){

        Producto producto =productMapper.toProducto(product);

       return   productoCrudRepository.findById(productId)
                 .map(
                         productoUpdate -> {
                             productoUpdate.setCantidadStock(producto.getCantidadStock());
                             productoUpdate.setEstado(producto.getEstado());
                             productoUpdate.setIdCategoria(producto.getIdCategoria());
                             productoUpdate.setNombre(producto.getNombre());
                             productoUpdate.setPrecioVenta(producto.getPrecioVenta());
                             return productMapper.toProduct(productoCrudRepository.save(productoUpdate));
                         }
         ).orElse(null);


    }

    //retornar por categoria los productos
   // public  List<Producto> getByCategoria(Long idCategora){
     //   return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategora);
    //}

    //retorna los productos menores a la cantida pasada ademas que su estado este activo
   // public Optional<Producto> getEscasos(Integer cantidad){
     //   return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    //}
    //buscar un producto por id
   // public Optional<Producto>  getProductoId(Long idProducto){
      //  return productoCrudRepository.findById(idProducto);
    //}
    //guardar un producto
    //public Producto save(Producto producto){
       // return productoCrudRepository.save(producto);
    //}
    //eliminar producto por id

}
