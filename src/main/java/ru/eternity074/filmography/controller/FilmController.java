package ru.eternity074.filmography.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.eternity074.filmography.model.Film;
import ru.eternity074.filmography.service.FilmService;

@Controller
public class FilmController {

	private int page;
	private FilmService filmService;

	@Autowired
	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping("/")
	public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
		this.page = page;
		List<Film> films = filmService.allFilms(page);
		int filmsCount = filmService.filmsCount();
		int pagesCount = (filmsCount + 9) / 10;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("films");
		modelAndView.addObject("filmsList", films);
		modelAndView.addObject("filmsCount", filmsCount);
		modelAndView.addObject("pagesCount", pagesCount);

		return modelAndView;
	}

	@GetMapping("/edit")
	public ModelAndView editPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editPage");

		return modelAndView;
	}

	@PostMapping(value = "/edit")
	public ModelAndView editFilm(@ModelAttribute("film") Film film, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/?page=" + this.page);
		filmService.edit(film);

		return modelAndView;
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editPage(@PathVariable("id") int id) {
		Film film = filmService.getById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editPage");
		modelAndView.addObject("film", film);

		return modelAndView;
	}

	@GetMapping(value = "/add")
	public ModelAndView addPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editPage");

		return modelAndView;
	}

	@PostMapping(value = "/add")
	public ModelAndView addFilm(@ModelAttribute("film") Film film, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/?page=" + this.page);
		filmService.add(film);

		return modelAndView;
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteFilm(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/?page=" + this.page);
		Film film = filmService.getById(id);
		filmService.delete(film);

		return modelAndView;
	}
}
