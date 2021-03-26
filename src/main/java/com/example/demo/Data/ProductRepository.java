package com.example.demo.Data;

import com.example.demo.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
