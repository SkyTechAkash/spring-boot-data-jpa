package com.akash.pg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.pg.model.Books;
import com.akash.pg.service.BooksServices;

@RestController
public class BooksController {

	@Autowired
	private BooksServices booksServices;

	// get All Books object..........
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBooks() {
		List<Books> allBooks = this.booksServices.getAllBooks();
		if(allBooks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// return this.booksServices.getAllBooks();
		return ResponseEntity.status(HttpStatus.FOUND).body(allBooks);
	}

	// get single book object.........
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Books> getBook(@PathVariable("bookId") int id) {
		Books book = this.booksServices.getBook(id);
		// return this.booksServices.getBook(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(book);
	}

	// add book object in the List........
	@PostMapping("/books")
	public ResponseEntity<String> addBooks(@RequestBody Books book) {
		Books books = this.booksServices.addBooks(book);
		if(books!=null) {
		return ResponseEntity.status(HttpStatus.CREATED).body("Book Details added. Thank you!");
		}
		return ResponseEntity.internalServerError().build();
	}

	// delete book object from the list.......
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<String> deleteBooks(@PathVariable("bookId") int id) {
		this.booksServices.deleteBook(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book Details deleted!");
	}

	@PutMapping("/books/{bookId}")
	public ResponseEntity<Books> updateBook(@PathVariable("bookId") int id, @RequestBody Books book) {
		Books books = booksServices.updateBook(id, book);
		// this.booksServices.updateBook(id, book);
		return ResponseEntity.ok(books);
	}

}
