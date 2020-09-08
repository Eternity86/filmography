package ru.eternity074.filmography.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.eternity074.filmography.dao.FilmDAO;
import ru.eternity074.filmography.model.Film;

@Service
public class FilmServiceImpl implements FilmService {

	private FilmDAO filmDAO;

	@Autowired
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@Override
	@Transactional
	public List<Film> allFilms(int page) {
		return filmDAO.allFilms(page);
	}

	@Override
	@Transactional
	public void add(Film film) {
		filmDAO.add(film);
	}

	@Override
	@Transactional
	public void delete(Film film) {
		filmDAO.delete(film);
	}

	@Override
	@Transactional
	public void edit(Film film) {
		filmDAO.edit(film);
	}

	@Override
	@Transactional
	public Film getById(int id) {
		return filmDAO.getById(id);
	}

	@Override
	@Transactional
	public int filmsCount() {
		return filmDAO.filmsCount();
	}

}
