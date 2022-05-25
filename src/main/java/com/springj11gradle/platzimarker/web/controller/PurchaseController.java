package com.springj11gradle.platzimarker.web.controller;

import com.springj11gradle.platzimarker.domain.Product;
import com.springj11gradle.platzimarker.domain.Purchase;
import com.springj11gradle.platzimarker.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
   public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(  purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Purchase>> getAllClient(@PathVariable("id") String clientId){
      return purchaseService.getByClient(clientId).map(
              purchases -> new ResponseEntity<>(purchases,HttpStatus.OK)
      ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
      //  purchase.setDate(LocalDateTime.now());

        try {
            return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

}
