package com.udj.course.resources;

import com.udj.course.domain.Client;
import com.udj.course.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

}
