package com.kodnest.ninthProject;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String club_name;
	@ManyToMany(mappedBy = "clubs")
	private Set<Student> students;

	public Club(int id, String club_name, Set<Student> students) {
		super();
		this.id = id;
		this.club_name = club_name;
		this.students = students;
	}

	public Club(String club_name, Set<Student> students) {
		super();
		this.club_name = club_name;
		this.students = students;
	}

	public Club(String club_name) {
		super();
		this.club_name = club_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getclub_name() {
		return club_name;
	}

	public void setclub_name(String club_name) {
		this.club_name = club_name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", club_name=" + club_name + ", students=" + students + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(club_name, id, students);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Club other = (Club) obj;
		return Objects.equals(club_name, other.club_name) && id == other.id && Objects.equals(students, other.students);
	}

}
