package com.example.demo.Data;

import com.example.demo.model.HouseHoldDetails;
import com.example.demo.model.HouseholdDetailsResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DataRepository extends CrudRepository<HouseHoldDetails, Integer> {

    @Transactional
    @Query(value = "SELECT * from households h inner join transactions b on h.hshd_num=b.hshd_num inner join products p on b.product_num=p.product_num where h.hshd_num = :hshd_num", nativeQuery = true)
    List<HouseHoldDetails> getHouseDetails(@Param("hshd_num")int hshd_num);
}
