package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdDetailsResponse {

    String status;

    List<HouseHoldDetails> houseHoldDetailsList;

    public HouseholdDetailsResponse(String status){
        this.status = status;
    }
}
