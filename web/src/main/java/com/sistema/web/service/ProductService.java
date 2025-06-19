package com.sistema.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.web.model.Product;
import com.sistema.web.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> listar() {
        return repo.findAll();
    }

    public Product salvar(Product p) {
        return repo.save(p);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }

    public Product buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }
}
