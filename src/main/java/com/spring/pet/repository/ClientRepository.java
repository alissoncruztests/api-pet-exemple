package com.spring.pet.repository;

import com.spring.pet.document.ClientDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;


public interface ClientRepository extends MongoRepository<ClientDocument, String> {

    Page<ClientDocument> findByCpf(LocalDateTime date, Pageable pageable);

}
