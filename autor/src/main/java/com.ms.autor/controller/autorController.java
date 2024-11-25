package com.ms.autor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.autor.entity.autorEntity;
import com.ms.autor.service.autorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class autorController {

    @Autowired
    autorService autorService;

    @GetMapping
    public ResponseEntity<List<autorEntity>> getAllProducts() {
        List<autorEntity> autores = autorService.getAllProductsService();
        return ResponseEntity.ok(autores);
    }
    // retorna os dados de um produto cujo id é fornecido
    @GetMapping("/{id}")
    public ResponseEntity<Optional<autorEntity>> getProductService(@PathVariable Integer id) {
        Optional<autorEntity> autor = autorService.getProductService(id);
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/names")
    public ResponseEntity<List<Object[]>> getNameAndIdService() {
        List<Object[]> autores = autorService.getNameAndIdService();

        if (autores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(autores);
    }


    //insere produto na base de dados
    @PostMapping("/add")
    public ResponseEntity<autorEntity> addProduct(@RequestBody autorEntity autor){
        autorEntity newProduct = autorService.insertProductService(autor);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    //atualiza produto na base de dados
    @PutMapping("/update")
    public ResponseEntity<autorEntity> updateProduct(@RequestBody autorEntity autor){
        autorEntity updatedProduct = autorService.updateProductService(autor);
        return ResponseEntity.ok(updatedProduct);
    }
    //delete os dados de um produto cujo id é fornecido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        autorService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}