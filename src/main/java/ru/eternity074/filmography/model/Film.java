package ru.eternity074.filmography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "year")
	private int year;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "watched")
	private boolean watched;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		return sb.append(this.id).append(" ").append(this.title).append(" ").append(this.year).append(" ")
				.append(this.genre).append(" ").append(this.watched).append(" ").toString();
	}

}
