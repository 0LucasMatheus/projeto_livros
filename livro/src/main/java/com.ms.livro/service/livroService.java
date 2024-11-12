package com.ms.livro.service;

import com.ms.livro.entity.livroEntity;
import com.ms.livro.repository.livroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class livroService {
    @Autowired
    livroRepository livroRepository;

    public List<livroEntity> getAllProductsService(){
        List<livroEntity> livros = livroRepository.findAll();
        return livros;
    }
    public Optional<livroEntity> getProductService(Integer id){
        return livroRepository.findById(id);
    }
    public livroEntity insertProductService(livroEntity livro){
        return livroRepository.save(livro);
    }
    public void deleteProductByIdService(Integer id){
        livroRepository.deleteById(id);
    }
    public livroEntity updateProductService(livroEntity livro){
        livroEntity updatedProduct = livroRepository.findById(livro.getId()).get();
        updatedProduct = livro;
        return livroRepository.save(updatedProduct);
    }
}

