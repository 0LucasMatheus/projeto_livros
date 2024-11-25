package com.ms.usuario.service;

import com.ms.usuario.entity.userEntity;
import com.ms.usuario.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    userRepository userRepository;

    public List<userEntity> getAllProductsService(){
        List<userEntity> usuarios = userRepository.findAll();
        return usuarios;
    }
    public Optional<userEntity> getProductService(Integer id){
        return userRepository.findById(id);
    }
    public userEntity insertProductService(userEntity user){
        return userRepository.save(user);
    }

    public userEntity compara(userEntity userRecebido) {
        // Tenta encontrar o usuário pelo nome
        userEntity userEncontrado = userRepository.findByNome(userRecebido.getNome());

        if (userEncontrado == null) {
            // Se não encontrar um usuário com o nome fornecido, retorna null
            return null;  // Retorna 404 no controlador
        }

        // Compara a senha do usuário recebido com a senha armazenada
        if (userEncontrado.getSenha().equals(userRecebido.getSenha())) {
            // Se a senha for correta, retorna o usuário encontrado
            return userEncontrado;  // Retorna o usuário encontrado com a role
        } else {
            // Se a senha for incorreta, retorna null
            return null;  // Retorna 403 no controlador
        }
    }


    public void deleteProductByIdService(Integer id){
        userRepository.deleteById(id);
    }
    public userEntity updateProductService(userEntity user){
        userEntity updatedProduct = userRepository.findById(user.getId()).get();
        updatedProduct = user;
        return userRepository.save(updatedProduct);
    }
}

