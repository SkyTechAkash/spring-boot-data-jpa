package com.akash.pg.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.akash.pg.model.Books;

@Repository
public interface BooksRepository extends CrudRepository<Books, Integer>{

}
