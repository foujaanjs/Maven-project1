package com.kodnest.sixthhibernate;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	String name;
	@Column
	int price;
	@Column
	int noOfPages;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author", referencedColumnName = "id")
	Author author;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String name, int price, int noOfPages, Author author) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.noOfPages = noOfPages;
		this.author = author;
	}

	public Book(String name, int price, int noOfPages, Author author) {
		super();
		this.name = name;
		this.price = price;
		this.noOfPages = noOfPages;
		this.author = author;
	}

	public Book(String name, int price, int noOfPages) {
		super();
		this.name = name;
		this.price = price;
		this.noOfPages = noOfPages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", noOfPages=" + noOfPages + ", author="
				+ author + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, name, noOfPages, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(name, other.name)
				&& noOfPages == other.noOfPages && price == other.price;
	}

}
