package com.kodnest.sixthhibernate;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	String name;
	@Column
	String gender;
	@Column
	int age;
	@Column
	String address;

	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(int id, String name, String gender, int age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}

	public Author(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public Author(String name, String gender, int age, String address) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", address=" + address
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, age, gender, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(address, other.address) && age == other.age && Objects.equals(gender, other.gender)
				&& id == other.id && Objects.equals(name, other.name);
	}

}
