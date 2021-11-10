package com.spring.datajpa.controller;

import com.spring.datajpa.model.Subcategory;
import com.spring.datajpa.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryController {


    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable("id") long id) {
        Optional<Subcategory> subcategoryData = subcategoryRepository.findById(id);

        if (subcategoryData.isPresent()) {
            return new ResponseEntity<>(subcategoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
