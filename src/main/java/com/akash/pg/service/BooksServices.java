package com.akash.pg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.pg.model.Books;
import com.akash.pg.repo.BooksRepository;

import jakarta.transaction.Transactional;

@Service
public class BooksServices {

	@Autowired
	private BooksRepository booksRepository;
	//private static List<Books> list = new ArrayList<>();
//
//	static {
//		list.add(new Books(10, "Harry Potter", "J.K Rowling", "Bloomsbury", 399));
//		list.add(new Books(11, "Harry Potter 1", "J.K Rowling", "Bloomsbury", 499));
//		list.add(new Books(12, "Harry Potter 2", "J.K Rowling", "Bloomsbury", 599));
//	}

	public List<Books> getAllBooks() {
		return (List<Books>) booksRepository.findAll();

	}

	public Books getBook(int id) {
		Books books = booksRepository.findById(id).get();
		//return list.stream().filter(bookId -> bookId.getId() == id).findFirst().get();
		return books;

	}

	
	public Books addBooks(Books book) {
		Books saveBooks = booksRepository.save(book);
		return saveBooks;
		//list.add(book);
	}

	public void deleteBook(int id) {
//		List<Books> collect = list.stream().filter(bookId -> bookId.getId() == id).collect(Collectors.toList());
//		for (Books book : collect) {
//			list.remove(book);
//		}
		booksRepository.deleteById(id);
	}

	public Books updateBook(int id, Books book) {
//		list.stream().filter(bookId -> bookId.getId() == id).map(b -> {
//			if (b.getId() == id) {
//				b.setPrice(book.getPrice());
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//				b.setPublisher(book.getPublisher());
//			}
//			return b;
//		}).collect(Collectors.toList());
		
		Books books = booksRepository.findById(id).get();
		books.setPrice(book.getPrice());
		books.setAuthor(book.getAuthor());
		books.setPublisher(book.getPublisher());
		books.setTitle(book.getTitle());
		booksRepository.save(books);
		return books;
	}

}
