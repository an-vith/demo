package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="households")
@NoArgsConstructor
public class HouseHoldEntity {

    @Id
    int hshd_num;

    String loyalty_flag;

    String age_range;

    String marital_status;

    String income_range;

    String homeowner_desc;

    String hshd_composition;

    String hshd_size;

    String children;

    public HouseHoldEntity(int hshd_num, String loyalty_flag, String age_range, String marital_status, String income_range, String homeowner_desc, String hshd_composition,String hshd_size, String children){
        this.hshd_num = hshd_num;
        this.loyalty_flag = loyalty_flag;
        this.age_range = age_range;
        this.marital_status = marital_status;
        this.income_range = income_range;
        this.homeowner_desc = homeowner_desc;
        this.hshd_composition = hshd_composition;
        this.hshd_size = hshd_size;
        this.children = children;
    }

    @Override
    public String toString(){
        return this.hshd_num+","+
                this.loyalty_flag+"," +
                this.age_range+","+
                this.marital_status+","+
                this.income_range+","+
                this.homeowner_desc+","+
                this.hshd_composition+","+
                this.hshd_size+","+
                this.children;
    }
}
