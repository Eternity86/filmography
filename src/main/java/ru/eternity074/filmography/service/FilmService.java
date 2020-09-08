package ru.eternity074.filmography.service;

import java.util.List;

import ru.eternity074.filmography.model.Film;

public interface FilmService {
	
	List<Film> allFilms(int page);

	void add(Film film);

	void delete(Film film);

	void edit(Film film);

	Film getById(int id);
	
	int filmsCount();
}
