package org.adaschool.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping(value = "/health")
public class HealthController {
    @RequestMapping(method = RequestMethod.GET)
    public String getResponse(){
        return "API working OK!";
    }
}
