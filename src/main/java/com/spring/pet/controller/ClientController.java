package com.spring.pet.controller;

import com.spring.pet.document.ClientDocument;
import com.spring.pet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<Page<ClientDocument>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                            @RequestParam(required = false) String flag){
        Page<ClientDocument> clientPage = clientService.findAll(pageable, flag);
        if(clientPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Page<ClientDocument>>(clientPage, HttpStatus.OK);
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDocument> getOneClient(@PathVariable(value="id") String id){
        Optional<ClientDocument> liveO = clientService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<ClientDocument>(liveO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientDocument> saveClient(@RequestBody @Valid ClientDocument client) {
        client.setRegistrationDate(LocalDateTime.now());
        return new ResponseEntity<ClientDocument>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value="id") String id) {
        Optional<ClientDocument> liveO = clientService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            clientService.delete(liveO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientDocument> updateClient(@PathVariable(value="id") String id,
                                                      @RequestBody @Valid ClientDocument clientDocument) {
        Optional<ClientDocument> liveO = clientService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            clientDocument.setId(liveO.get().getId());
            return new ResponseEntity<ClientDocument>(clientService.save(clientDocument), HttpStatus.OK);
        }
    }
}
