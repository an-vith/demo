package com.example.demo.service;

import com.example.demo.Data.DataRepository;
import com.example.demo.Data.HouseHoldRepository;
import com.example.demo.Data.ProductRepository;
import com.example.demo.Data.TransactionRepository;
import com.example.demo.model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HouseHoldService {

    private HouseHoldRepository houseHoldRepository;
    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;
    private DataRepository dataRepository;

    @Autowired
    public HouseHoldService(HouseHoldRepository houseHoldRepository, ProductRepository productRepository, TransactionRepository transactionRepository, DataRepository dataRepository){
        this.houseHoldRepository = houseHoldRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
        this.dataRepository = dataRepository;
    }

    public HouseholdDetailsResponse getHouseDetails(int houseNum){
        try {
            List<HouseHoldDetails> response = dataRepository.getHouseDetails(houseNum);
            if (response.size() > 0) {
                System.out.println("Successful Response for HouseHold Num:"+houseNum);
                return new HouseholdDetailsResponse("success", response);
            } else {
                System.out.println("No Records Found for HouseHold Num:"+houseNum);
                return new HouseholdDetailsResponse("NoRecordsFound");
            }
        }catch(Exception ex){
            System.out.println("Exception occurred for HouseHold Num:"+houseNum);
            return new HouseholdDetailsResponse("Error");
        }
    }

    public UploadResponse processFileUpload(String type, MultipartFile file){
        if(type.equalsIgnoreCase("households")){
            return uploadHouseholds(file);
        }else if(type.equalsIgnoreCase("products")){
            return uploadProducts(file);
        }else if(type.equalsIgnoreCase("transactions")){
            return uploadTransactions(file);
        }else return new UploadResponse("UnRecognized File or Table schema");
    }

    public UploadResponse uploadHouseholds(MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String currentLine;
            List<HouseHoldEntity> houseHoldEntities = new ArrayList<>();
            bufferedReader.readLine();
            while( (currentLine = bufferedReader.readLine()) != null){
                String[] values = currentLine.split(",");
                System.out.println(values[0]);
                houseHoldEntities.add(new HouseHoldEntity(Integer.parseInt(values[0].trim()), values[1].trim(), values[2].trim(), values[3].trim(), values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim(), values[8].trim()));
            }
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(HouseHoldEntity houseHoldEntity : houseHoldEntities){
                executorService.submit(new Runnable(){
                    @Override
                    public void run(){
                        houseHoldRepository.save(houseHoldEntity);
                    }
                });
            }
            return new UploadResponse("Data is being Successfully Uploaded");
        } catch (IOException e) {
            return new UploadResponse("Error Occurred");
        }
    }

    public UploadResponse uploadProducts(MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String currentLine;
            List<ProductEntity> productEntities = new ArrayList<>();
            bufferedReader.readLine();
            while( (currentLine = bufferedReader.readLine()) != null){
                String[] values = currentLine.split(",");
                System.out.println(values[0]);
                productEntities.add(new ProductEntity(Integer.parseInt(values[0].trim()), values[1].trim(), values[2].trim(), values[3].trim(), values[4].trim()));
            }
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(ProductEntity productEntity : productEntities){
                executorService.submit(new Runnable(){
                    @Override
                    public void run(){
                        productRepository.save(productEntity);
                    }
                });
            }
            return new UploadResponse("Data is being Successgully Uploaded");
        } catch (IOException e) {
            return new UploadResponse("Error Occurred");
        }
    }

    public UploadResponse uploadTransactions(MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String currentLine;
            List<TransactionEntity> transactionEntities = new ArrayList<>();
            bufferedReader.readLine();
            while( (currentLine = bufferedReader.readLine()) != null){
                String[] values = currentLine.split(",");
                System.out.println(values[0]);
                transactionEntities.add(new TransactionEntity(Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim()), values[2].trim(), Integer.parseInt(values[3].trim()), Double.valueOf(values[4].trim()), Integer.valueOf(values[5].trim()), values[6].trim(), Integer.valueOf(values[7].trim()), Integer.valueOf(values[8].trim())));
            }

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(TransactionEntity transactionEntity : transactionEntities){
                executorService.submit(new Runnable(){
                    @Override
                    public void run(){
                        transactionRepository.save(transactionEntity);
                    }
                });
            }
            return new UploadResponse("Data is being Successfully Uploaded");
        }
        catch (IOException e) {
            System.out.println(e);
            return new UploadResponse(e.toString());
        }
    }
}
