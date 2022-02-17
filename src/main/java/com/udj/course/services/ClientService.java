package com.udj.course.services;

import com.udj.course.Constants;
import com.udj.course.domain.Client;
import com.udj.course.dto.ClientDTO;
import com.udj.course.repositories.ClientRepository;
import com.udj.course.services.exceptions.DataIntegrityException;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Integer id) {
        Optional<Client> op = repository.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException(Constants.OBJECT_NOT_FOUND));
    }

    public Page<Client> findPage(int page, int linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client insert(Client obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Client update(Client obj) {
        Client client = findById(obj.getId());
        updateData(client, obj);
        return repository.save(client);
    }

    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(Constants.CANT_DELETE);
        }
    }

    public Client fromDT0(ClientDTO obj){
        return new Client(obj.getId(), obj.getName(), obj.getEmail());
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
