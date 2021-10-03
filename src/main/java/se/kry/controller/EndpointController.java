package se.kry.controller;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kry.dto.EndpointDTO;
import se.kry.entity.Endpoint;
import se.kry.exception.InvalidRequest;
import se.kry.service.EndpointServiceInt;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EndpointController {

    @Autowired
    EndpointServiceInt serviceInt;
    Gson gson=new Gson();


    @PostMapping(path = "/add", produces = "application/json",consumes = "application/json")
    public String add(@RequestBody EndpointDTO endpoint){

     try {
         UrlValidator urlValidator=new UrlValidator();
         if(!urlValidator.isValid(endpoint.getUrl())){
             return "Invalid Url";
         }
         serviceInt.add(convertDTOtoEntity(endpoint));

     }catch (Exception e){
         e.printStackTrace();
       return  e.getMessage();
     }

     return "OK";
    }

    @GetMapping (path = "/getall", produces = "application/json")
    public String getAll(){

        try {
        return   gson.toJson(serviceInt.getEndpoints());
        }catch (Exception e){
            e.printStackTrace();
            return  e.getMessage();
        }

    }

    @GetMapping (path = "/getname", produces = "application/json")
    public String getName(String name){

        try {
            return   gson.toJson(serviceInt.get(name));
        }catch (Exception e){
            e.printStackTrace();
            return  e.getMessage();
        }

    }


    @PutMapping(path = "/update", produces = "application/json",consumes = "application/json")
    public String update(@RequestBody EndpointDTO endpoint){

        try {
            serviceInt.update(convertDTOtoEntity(endpoint));
        }catch (Exception e){
            e.printStackTrace();
            return  e.getMessage();
        }

        return "OK";
    }

    @DeleteMapping(path = "/delete", produces = "application/json")
    public String update(@RequestParam String name){

        try {
            serviceInt.delete(name);
        }catch (Exception e){
            e.printStackTrace();
            return  e.getMessage();
        }

        return "OK";
    }

    private Endpoint convertDTOtoEntity(EndpointDTO endpoint){
        if(endpoint == null || StringUtils.isEmpty(endpoint.getName()) || StringUtils.isEmpty(endpoint.getUrl())){
            throw  new InvalidRequest("Invalid Request") ;
        }
            Endpoint tmp= gson.fromJson(gson.toJson(endpoint), Endpoint.class);
         tmp.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));
        tmp.setUpdatedAt(new Timestamp(Instant.now().toEpochMilli()));
      return  tmp;
    }

}
