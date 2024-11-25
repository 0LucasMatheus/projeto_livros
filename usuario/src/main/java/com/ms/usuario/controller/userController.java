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
@RequestMapping("/usuario")
public class userController {

    @Autowired
    userService userService;

    @GetMapping
    public ResponseEntity<List<userEntity>> getAllProducts() {
        List<userEntity> users = userService.getAllProductsService();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(users);
    }

    // Retorna os dados de um produto cujo ID é fornecido
    @GetMapping("/{id}")
    public ResponseEntity<userEntity> getProductService(@PathVariable Integer id) {
        Optional<userEntity> user = userService.getProductService(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user.get());
    }


    // Insere produto na base de dados
    @PostMapping("/add")
    public ResponseEntity<userEntity> addProduct(@RequestBody userEntity user) {
        userEntity newProduct = userService.insertProductService(user);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<userEntity> loginService(@RequestBody userEntity user) {
        userEntity userRecebido = userService.compara(user);
        if (userRecebido == null) {
            // Se não encontrar o usuário ou as credenciais estiverem incorretas
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // Retorna 403 se não for válido
        }
        // Caso as credenciais sejam válidas
        return new ResponseEntity<>(userRecebido, HttpStatus.OK);  // Retorna os dados do usuário
    }

    // Atualiza produto na base de dados
    @PutMapping("/update")
    public ResponseEntity<userEntity> updateProduct(@RequestBody userEntity user) {
        Optional<userEntity> userexist = userService.getProductService(user.getId());
        if (userexist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userEntity useratualizado = userService.updateProductService(user);
        return ResponseEntity.ok(useratualizado);
    }

    // Deleta os dados de um produto cujo ID é fornecido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<userEntity> userexist = userService.getProductService(id);
        if (userexist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }
}
