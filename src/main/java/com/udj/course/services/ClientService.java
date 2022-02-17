package com.udj.course.services;

import com.udj.course.Constants;
import com.udj.course.domain.Address;
import com.udj.course.domain.City;
import com.udj.course.domain.Client;
import com.udj.course.domain.enums.ClientType;
import com.udj.course.dto.ClientDTO;
import com.udj.course.dto.ClientNewDTO;
import com.udj.course.repositories.AddressRepository;
import com.udj.course.repositories.ClientRepository;
import com.udj.course.services.exceptions.DataIntegrityException;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

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

    @Transactional //garante que salve tudo em uma so transação
    public Client insert(Client obj) {
        obj.setId(null);
        obj = repository.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
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

    public Client fromDT0(ClientDTO obj) {
        return new Client(obj.getName(), obj.getEmail(), null, null);
    }

    public Client fromDT0(ClientNewDTO obj) {
        Client client = new Client(obj.getName(), obj.getEmail(),
                obj.getCpfOrCnpj(), ClientType.getById(obj.getType()));

        City city = new City(obj.getCityId(), null, null);

        Address address = new Address(
                city,
                client,
                obj.getPublicPlace(),
                obj.getNumber(),
                obj.getAddressReference(),
                obj.getNeighborhood(),
                obj.getZipCode()
        );

        client.getAddresses().add(address);
        client.getPhoneNumbers().add(obj.getPhone1());

        if(obj.getPhone2() != null){client.getPhoneNumbers().add(obj.getPhone2());}
        if(obj.getPhone3() != null){client.getPhoneNumbers().add(obj.getPhone3());}

        return client;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
