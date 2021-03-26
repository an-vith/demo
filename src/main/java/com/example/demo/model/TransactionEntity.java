package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="transactions")
@NoArgsConstructor
public class TransactionEntity {

    @Id
    int basket_num;

    int hshd_num;

    String date;

    int product_num;

    double spend;

    int units;

    String store_region;

    int week_num;

    int year;

    public TransactionEntity(int basket_num, int hshd_num, String date, int product_num, double spend, int units,String store_region, int week_num, int year){
        this.basket_num = basket_num;
        this.hshd_num = hshd_num;
        this.date = date;
        this.product_num = product_num;
        this.spend = spend;
        this.units = units;
        this.store_region =store_region;
        this.week_num = week_num;
        this.year = year;
    }

}
