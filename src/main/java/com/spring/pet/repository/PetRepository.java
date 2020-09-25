package com.spring.pet.repository;

import com.spring.pet.document.PetDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;


public interface PetRepository extends MongoRepository<PetDocument, String> {

}
