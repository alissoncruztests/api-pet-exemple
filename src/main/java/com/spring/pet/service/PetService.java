package com.spring.pet.service;

import com.spring.pet.document.PetDocument;
import com.spring.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Page<PetDocument> findAll(Pageable pageable, String specie){

        return petRepository.findAll(pageable);

    }

    public Optional<PetDocument> findById(String id){
        return petRepository.findById(id);
    }

    public PetDocument save(PetDocument petDocument){
        return petRepository.save(petDocument);
    }

    public void delete(PetDocument petDocument){
        petRepository.delete(petDocument);
    }
}
