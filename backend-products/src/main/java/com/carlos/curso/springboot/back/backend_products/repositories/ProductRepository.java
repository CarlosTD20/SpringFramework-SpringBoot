package com.carlos.curso.springboot.back.backend_products.repositories;

import com.carlos.curso.springboot.back.backend_products.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
