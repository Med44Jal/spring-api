package com.spring.datajpa.repository;

import com.spring.datajpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    List<Author> findByFullnameContaining(String fullname);
}
