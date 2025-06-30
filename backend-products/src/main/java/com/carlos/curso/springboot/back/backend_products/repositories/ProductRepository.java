package com.carlos.curso.springboot.back.backend_products.repositories;

import com.carlos.curso.springboot.back.backend_products.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:4200/"})
@CrossOrigin(origins = "*")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
