package com.carlos.curso.springboot.app.springboot_crud.services;

import com.carlos.curso.springboot.app.springboot_crud.entities.Product;
import com.carlos.curso.springboot.app.springboot_crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = this.repository.findById(id);
        if (productOptional.isPresent()) {
            Product prod = productOptional.orElseThrow();

            prod.setSku(product.getSku());
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            return Optional.of(this.repository.save(prod));
        };

        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long  id) {
        Optional<Product> productOptional = this.repository.findById(id);
        productOptional.ifPresent(prod -> {
            this.repository.delete(prod);
        });

        return productOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return this.repository.existsBySku(sku);
    }
}
