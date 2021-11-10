package com.spring.datajpa.controller;

import com.spring.datajpa.model.Author;
import com.spring.datajpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AuthorController {


    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false) String fullname) {
        try {
            List<Author> authors = new ArrayList<Author>();

            if (fullname == null)
                authorRepository.findAll().forEach(authors::add);
            else
                authorRepository.findByFullnameContaining(fullname).forEach(authors::add);

            if (authors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") long id) {
        Optional<Author> authorData = authorRepository.findById(id);

        if (authorData.isPresent()) {
            return new ResponseEntity<>(authorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Author> create(@RequestBody Author author) {
        try {
            Author _author = authorRepository.save(new Author(author.getFullname(), author.getAge(), author.getNationality()));
            return new ResponseEntity<>(_author, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") long id, @RequestBody Author author) {
        Optional<Author> authorData = authorRepository.findById(id);

        if (authorData.isPresent()) {
            Author _author = authorData.get();
            _author.setFullname(author.getFullname());
            _author.setAge(author.getAge());
            _author.setNationality(author.getNationality());
            return new ResponseEntity<>(authorRepository.save(_author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
