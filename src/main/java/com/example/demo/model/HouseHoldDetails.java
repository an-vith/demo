package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HouseHoldDetails {

    @Id
    int hshd_num;

    int basket_num;

    String date;

    int product_num;

    String department;

    String commodity;

    double spend;

    int units;

    String store_region;

    int week_num;

    int year;

    String loyalty_flag;

    String age_range;

    String marital_status;

    String income_range;

    String homeowner_desc;

    String hshd_composition;

    String hshd_size;

    String children;
}
