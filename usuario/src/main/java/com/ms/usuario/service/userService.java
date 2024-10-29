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
    public void deleteProductByIdService(Integer id){
        userRepository.deleteById(id);
    }
    public userEntity updateProductService(userEntity user){
        userEntity updatedProduct = userRepository.findById(user.getId()).get();
        updatedProduct = user;
        return userRepository.save(updatedProduct);
    }
}

