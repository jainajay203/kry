package se.kry.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(name = "1", path = "/endpoint1",produces = "application/json")
    public ResponseEntity test1(){
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @GetMapping(name = "1",path = "/endpoint2",produces = "application/json")
    public String test2(){
        return "OK";
    }
    @GetMapping(name = "1",path = "/endpoint3",produces = "application/json")
    public String test3(){

        return "OK";
    }
    @GetMapping(name = "1",path = "/endpoint4",produces = "application/json")
    public String test4(){
        return "FAIL";
    }

}
