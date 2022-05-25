package com.springj11gradle.platzimarker.web.controller;

import com.springj11gradle.platzimarker.domain.Product;
import com.springj11gradle.platzimarker.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation("Retorna los productos del supermercado")
    @ApiResponse(code=200,message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @ApiOperation("Retorna un  producto del supermercado")
    @ApiResponses({
            @ApiResponse(code=200,message = "OK"),
            @ApiResponse(code=404,message = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product>  getProductId(@ApiParam(value = "el id del producto",required = true,example = "7")
                                                     @PathVariable("id") long id){
        return productService.getProductId(id)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") long id){

        final Optional<List<Product>> products = productService.getProductByCategoria(id);
            if (products.get().isEmpty()) {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>(products.get(), HttpStatus.OK);
            }

    }
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity delete(@PathVariable("id") long id){

        return new ResponseEntity(productService.delete(id)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable long id,@RequestBody Product product){

        Product product1 =productService.update(id,product);
        if(product1 !=null){
            return new ResponseEntity<>(product1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
