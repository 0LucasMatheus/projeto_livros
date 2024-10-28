package com.ms.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.usuario.entity.userEntity;
import com.ms.usuario.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class userController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<userEntity>> getAllProducts() {
        List<userEntity> livros = userService.getAllProductsService();
        return ResponseEntity.ok(livros);
    }
    // retorna os dados de um produto cujo id é fornecido
    @GetMapping("/{id}")
    public ResponseEntity<Optional<userEntity>> getProductService(@PathVariable Integer id) {
        Optional<userEntity> livro = livroService.getProductService(id);
        return ResponseEntity.ok(livro);
    }
    //insere produto na base de dados
    @PostMapping("/add")
    public ResponseEntity<userEntity> addProduct(@RequestBody userEntity livro){
        userEntity newProduct = livroService.insertProductService(livro);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    //atualiza produto na base de dados
    @PutMapping("/update")
    public ResponseEntity<userEntity> updateProduct(@RequestBody userEntity livro){
        userEntity updatedProduct = livroService.updateProductService(livro);
        return ResponseEntity.ok(updatedProduct);
    }
    //delete os dados de um produto cujo id é fornecido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        livroService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}