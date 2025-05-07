package com.carlos.springboot.di.app.springboot_di.services;

import com.carlos.springboot.di.app.springboot_di.models.Product;
import com.carlos.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${config.price.tax}")
    private Double taxValue;

    //    @Autowired
    //    @Qualifier("productList")
    private ProductRepository repository;
    private Environment environment;

    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            //            Double priceImp = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
            Double priceImp = p.getPrice() * taxValue;
            //            Product newProduct = new Product(p.getId(), p.getName(), priceImp.longValue());

            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceImp.longValue());
            return newProduct;

            //            p.setPrice(priceImp.longValue());
            //            return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}