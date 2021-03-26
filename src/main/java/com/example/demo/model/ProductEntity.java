package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="products")
@NoArgsConstructor
public class ProductEntity {

    @Id
    int product_num;

    String department;

    String commodity;

    String brand_type;

    String naturalorganicflag;

    public ProductEntity(int product_num, String department, String commodity, String brand_type, String naturalorganicflag){
        this.product_num = product_num;
        this.department = department;
        this.commodity = commodity;
        this.brand_type = brand_type;
        this.naturalorganicflag = naturalorganicflag;
    }
}
