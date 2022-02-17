package com.udj.course.resources;

import com.udj.course.domain.Client;
import com.udj.course.dto.ClientDTO;
import com.udj.course.dto.ClientNewDTO;
import com.udj.course.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> clients = service.findAll();
        List<ClientDTO> clientDTOList = clients.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        Page<Client> clientPage = service.findPage(page, linesPerPage, direction, orderBy);
        Page<ClientDTO> clientDTOS = clientPage.map(ClientDTO::new);

        return ResponseEntity.ok().body(clientDTOS);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO obj) {
        Client client = service.fromDT0(obj);
        client = service.insert(client);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id) {
        Client client = service.fromDT0(clientDTO);
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
