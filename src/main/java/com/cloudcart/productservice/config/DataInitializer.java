package com.cloudcart.productservice.config;

import com.cloudcart.productservice.model.Product;
import com.cloudcart.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product(1L, "Laptop", 75000.0));
                productRepository.save(new Product(2L, "Keyboard", 2500.0));
                productRepository.save(new Product(3L, "Mouse", 1200.0));

                System.out.println("Sample products inserted successfully.");
            }
        };
    }
}
