package com.example.demo.controller;

import com.example.demo.Data.DataRepository;
import com.example.demo.model.HouseHoldDetails;
import com.example.demo.model.HouseholdDetailsResponse;
import com.example.demo.model.UploadResponse;
import com.example.demo.model.UserServiceResponse;
import com.example.demo.service.HouseHoldService;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RestController
public class CloudController {

    private HouseHoldService houseHoldService;
    private UserService userService;

    @Autowired
    public CloudController(HouseHoldService houseHoldService, UserService userService){
        this.houseHoldService = houseHoldService;
        this.userService = userService;
    }

    @GetMapping(value="/gethousedetails/{id}", produces={"application/json"})
    public HouseholdDetailsResponse getHouseDetails(@PathVariable(value="id") int houseNum){
        System.out.println("Received Request for Get House Details with HouseNum:"+houseNum);
        HouseholdDetailsResponse response = houseHoldService.getHouseDetails(houseNum);
        return response;
    }

    @PostMapping(value="/upload", produces={"application/json"})
    public ResponseEntity<UploadResponse> uploadNew(@RequestParam("type") String type,@RequestParam("file") MultipartFile file){
        System.out.println("received Request with Type: "+type);
        try {
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse = houseHoldService.processFileUpload(type, file);
            return ResponseEntity.status(200).body(uploadResponse);
        }catch(Exception ex){
            return ResponseEntity.status(500).body(new UploadResponse("Error Occurred"));
        }
    }

    @PostMapping(value="/register", produces={"application/json"})
    public ResponseEntity<UserServiceResponse> registerUser(@RequestParam("rusername") String username, @RequestParam("rpassword") String password, @RequestParam("remail") String email){
        String response = "";
        try{
            response = userService.registerUser(username, password, email);
        }catch(Exception ex){
            response = "Exception Occurred. Please Retry.";
        }
        return ResponseEntity.status(200).body(new UserServiceResponse(response));
    }

    @PostMapping(value="/login", produces={"application/json"})
    public ResponseEntity<UserServiceResponse> login(@RequestParam("lusername") String username, @RequestParam("lpassword") String password){
        String response = "";
        try{
            response = userService.login(username, password);
        }catch(Exception ex){
            response = "Exception Occurred. Please Retry.";
        }
        return ResponseEntity.status(200).body(new UserServiceResponse(response));
    }
}
