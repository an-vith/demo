package com.example.demo.Data;

import com.example.demo.model.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, String> {
}
