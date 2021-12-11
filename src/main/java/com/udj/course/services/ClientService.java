package com.udj.course.services;

import com.udj.course.domain.Client;
import com.udj.course.repositories.ClientRepository;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private static final String OBJECT_NOT_FOUND = ClientService.class.getSimpleName()+ ": OBJETO NÃO ENCONTRADO";

    @Autowired
    private ClientRepository repository;

    public Client getById(Integer id){
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }
}
