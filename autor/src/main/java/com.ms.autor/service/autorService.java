package com.ms.autor.service;

import com.ms.autor.entity.autorEntity;
import com.ms.autor.repository.autorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class autorService {
    @Autowired
    autorRepository autorRepository;

    public List<autorEntity> getAllProductsService(){
        List<autorEntity> autores = autorRepository.findAll();
        return autores;
    }
    public Optional<autorEntity> getProductService(Integer id){
        return autorRepository.findById(id);
    }
    public autorEntity insertProductService(autorEntity autor){
        return autorRepository.save(autor);
    }
    public void deleteProductByIdService(Integer id){
        autorRepository.deleteById(id);
    }
    public autorEntity updateProductService(autorEntity autor){
        autorEntity updatedProduct = autorRepository.findById(autor.getId()).get();
        updatedProduct = autor;
        return autorRepository.save(updatedProduct);
    }
}

