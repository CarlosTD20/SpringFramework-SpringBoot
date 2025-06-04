package com.carlos.curso.springboot.app.springboot_crud.repositories;

import com.carlos.curso.springboot.app.springboot_crud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
