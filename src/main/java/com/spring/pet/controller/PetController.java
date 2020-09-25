package com.spring.pet.controller;

import com.spring.pet.document.PetDocument;
import com.spring.pet.service.PetService;
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
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping("/pets")
    public ResponseEntity<Page<PetDocument>> getAllPets(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                          @RequestParam(required = false) String specie){
        Page<PetDocument> petPage = petService.findAll(pageable, specie);
        if(petPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Page<PetDocument>>(petPage, HttpStatus.OK);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<PetDocument> getOnePet(@PathVariable(value="id") String id){
        Optional<PetDocument> petO =petService.findById(id);
        if(!petO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<PetDocument>(petO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<PetDocument> savePet(@RequestBody @Valid PetDocument pet) {
        pet.setRegistrationDate(LocalDateTime.now());
        return new ResponseEntity<PetDocument>(petService.save(pet), HttpStatus.CREATED);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value="id") String id) {
        Optional<PetDocument> petO = petService.findById(id);
        if(!petO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            petService.delete(petO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<PetDocument> updatePet(@PathVariable(value="id") String id,
                                                      @RequestBody @Valid PetDocument petDocument) {
        Optional<PetDocument> liveO = petService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            petDocument.setId(liveO.get().getId());
            return new ResponseEntity<PetDocument>(petService.save(petDocument), HttpStatus.OK);
        }
    }
}
