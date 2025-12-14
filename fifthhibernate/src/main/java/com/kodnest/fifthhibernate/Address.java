package com.kodnest.fifthhibernate;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	String city;
	@Column
	String street;
	@Column
	String state;
	@Column
	String zipcode;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int id, String city, String street, String state, String zipcode) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.state = state;
		this.zipcode = zipcode;
	}

	public Address(String city, String street, String state, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.state = state;
		this.zipcode = zipcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", street=" + street + ", state=" + state + ", zipcode="
				+ zipcode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, id, state, street, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && id == other.id && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street) && Objects.equals(zipcode, other.zipcode);
	}

}
