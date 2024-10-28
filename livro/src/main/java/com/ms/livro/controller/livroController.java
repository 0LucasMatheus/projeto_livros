package com.ms.livro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.livro.entity.livroEntity;
import com.ms.livro.service.livroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class livroController {

    @Autowired
    livroService livroService;

    @GetMapping
    public ResponseEntity<List<livroEntity>> getAllProducts() {
        List<livroEntity> livros = livroService.getAllProductsService();
        return ResponseEntity.ok(livros);
    }
    // retorna os dados de um produto cujo id é fornecido
    @GetMapping("/{id}")
    public ResponseEntity<Optional<livroEntity>> getProductService(@PathVariable Integer id) {
        Optional<livroEntity> livro = livroService.getProductService(id);
        return ResponseEntity.ok(livro);
    }
    //insere produto na base de dados
    @PostMapping("/add")
    public ResponseEntity<livroEntity> addProduct(@RequestBody livroEntity livro){
        livroEntity newProduct = livroService.insertProductService(livro);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    //atualiza produto na base de dados
    @PutMapping("/update")
    public ResponseEntity<livroEntity> updateProduct(@RequestBody livroEntity livro){
        livroEntity updatedProduct = livroService.updateProductService(livro);
        return ResponseEntity.ok(updatedProduct);
    }
    //delete os dados de um produto cujo id é fornecido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        livroService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}