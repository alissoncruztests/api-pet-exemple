package com.spring.pet.service;

import com.spring.pet.document.ClientDocument;
import com.spring.pet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Page<ClientDocument> findAll(Pageable pageable, String flag){
        return clientRepository.findAll(pageable);
    }

    public Optional<ClientDocument> findById(String id){
        return clientRepository.findById(id);
    }

    public ClientDocument save(ClientDocument clientDocument){
        return clientRepository.save(clientDocument);
    }

    public void delete(ClientDocument clientDocument){
        clientRepository.delete(clientDocument);
    }
}
