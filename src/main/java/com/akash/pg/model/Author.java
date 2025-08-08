package com.akash.pg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Author {

	Author() {
	}

	@Id
	private int id;

	@Column(name = "auth_firstName")
	private String authorFirstName;

	@Column(name = "auth_secondName")
	private String authorLastName;

	@OneToOne(mappedBy = "author")
	@JsonBackReference
	private Books books;

	public Author(int id, String authorFirstName, String authorLastName, Books books) {
		super();
		this.id = id;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.books = books;
	}

	/**
	 * @return the books
	 */
	public Books getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(Books books) {
		this.books = books;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the authorFirstName
	 */
	public String getAuthorFirstName() {
		return authorFirstName;
	}

	/**
	 * @param authorFirstName the authorFirstName to set
	 */
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	/**
	 * @return the authorLastName
	 */
	public String getAuthorLastName() {
		return authorLastName;
	}

	/**
	 * @param authorLastName the authorLastName to set
	 */
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName
				+ ", books=" + books + "]";
	}

}
