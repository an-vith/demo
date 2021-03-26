package com.example.demo.Data;

import com.example.demo.model.HouseHoldEntity;
import org.springframework.data.repository.CrudRepository;

public interface HouseHoldRepository extends CrudRepository<HouseHoldEntity, String> {
}
