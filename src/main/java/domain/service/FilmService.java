/**
 * 
 * @author Dominik
 * @version 1.0
 * 
 * Pakiet zawiera implementacje kontrolera serwisu filmowego dla
 * modelu danych i restowego API 
 * @see domain.Film
 * @see domain.Actor
 * 
 */
package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;
import domain.Film;

/**
 * 
 * Klasa implementuje kontroler serwisu filmowego.
 * 
 */
public class FilmService {
	
	/**
	 * Zmienna stanowi bazę danych.
	 */
	private static ArrayList<Film> db = new ArrayList<Film>();
	/**
	 * Zmienna ustawia kolejne numery id w bazie filmów
	 * @see domain.service.FilmService#currentActorId
	 */
	private static int currentFilmId = 1;
	/**
	 * Zmienna ustawia kolejne numery id w bazie aktorów
	 * @see domain.service.FilmService#currentFilmId
	 */
	private static int currentActorId = 1;
	
	/**
	 * @return Zwracana jest lista wszystkich filmów
	 */
	public List<Film> getAll() {
		return this.db;
	}
	
	/**
	 * Wyswietlenie filmu o podanym id
	 * @param id numer id filmu, ktory chcemy wyswietlic
	 * @return film o podanym id
	 */
	public Film getFilm(int id) {
		for(Film f : db) {
			if(f.getId()==id) return f;
		}
		return null;
	}
	
	/**
	 * Dodanie nowego filmu do listy filmów
	 * @param film nowy film
	 */
	public void addFilm(Film film) {
		film.setId(currentFilmId);
		db.add(film);
		++currentFilmId;
	}
	
	/**
	 * aktualizacja danych o zapisanym filmie
	 * @param film zaktualizowane dane o filmie
	 */
	public void updateFilm(Film film) {
		for(Film f : db) {
			if(f.getId()==film.getId()) {
				if( ! film.getTitle().equals("")) f.setTitle(film.getTitle());
				if(film.getYear() != 0) f.setYear(film.getYear());
				
				if( ! film.getActor().isEmpty()) {
					for(Actor a : film.getActor()) {
						f.addActor(a);
					}
				}
				
				if( ! film.getComment().isEmpty()) {
				for(String s : film.getComment()) {
					f.addComment(s);
				}
				}
				
				if( ! film.getRating().isEmpty()) {
				for (int r : film.getRating()) {
					f.addRating(r);
				}
				}
			}
		}
	}
	
	/**
	 * Wyswietlenie listy komentarzy danego filmu
	 * @param id numer id filmu
	 * @return lista kometarzy do filmu
	 */
	public List<String> getComment(int id) {
		return db.get(id).getComment();
	}
	
	/**
	 * Ustawienie komentarza dla danego filmu
	 * @param title tytul filmu
	 * @param comment tresc komentarza
	 */
	public void addComment(String title, String comment) {
		for (Film film : db) {
			if(film.getTitle().equalsIgnoreCase(title))   film.addComment(comment);
		}
	}
	
	/**
	 * Usuniecie komentarza dla danego filmu
	 * @param title tytul filmu
	 * @param comment tresc komentarza do usuniecia
	 */
	public void deleteComment(String title, String comment) {
		for (Film film : db) {
			if(film.getTitle().equalsIgnoreCase(title)) {
				for(String s : film.getComment()) {
					if(s.equalsIgnoreCase(comment)) {
						film.getComment().remove(comment);
					}
				}
			}
		}
	}
	
	/**
	 * Wystawienie oceny dla danego filmu
	 * @param title tytul filmu
	 * @param rating wysokosc oceny w skali od 0 do 10
	 * @throws IllegalArgumentException wyjatek dla nieprawidlowej wartosci oceny
	 */
	public void setRating(String title, int rating) throws IllegalArgumentException{
		if (rating >= 0 && rating <= 10) {
			for (Film film : db) {
				if(film.getTitle().equalsIgnoreCase(title)) film.addRating(rating);
			}
		} else throw new IllegalArgumentException("Ocena filmu musi byc w skali od 0 do 10");
	}
	
	/**
	 * Pokazuje srednia ocen dla filmu o danym tytule
	 * @param title tytul filmu
	 * @return srednia wartosc ocen dla filmu
	 */
	public float getAverageRating(String title) {
		float average = 0;
		int ratingCount = 0;
		for (Film film : db) {
			if(film.getTitle().equalsIgnoreCase(title)) {
				ratingCount = film.getRating().size();
				for(Integer rating : film.getRating()) {
					average += rating;
				}
			}
		}
		return average /= ratingCount;
	}
	
	/**
	 * Dodaje aktora do danego filmu
	 * @param title tytul filmu
	 * @param actor dane aktora
	 */
	public void addActor(String title, Actor actor) {
		for(Film film : db) {
			if(film.getTitle().equalsIgnoreCase(title)) {
				for(Actor a : film.getActor()) {
					a.setId(currentActorId);
					film.getActor().add(actor);
					++currentActorId;
				}
			}
		}
	}
	
	/**
	 * Przydzielenie filmu danemu aktorowi
	 * @param filmTitle tytul filmu
	 * @param actorName imie aktora
	 */
	public void addFilmToActor(String filmTitle, String actorName) {
		for(Film film : db) {
			if(film.getTitle().equalsIgnoreCase(filmTitle)) {
				for(Actor actor : film.getActor()) {
					if(actor.getName().equalsIgnoreCase(actorName)) {
						actor.addFilm(film);
					}
				}
			}
		}
	}
	
	/**
	 * Wyswietlenie aktorow danego filmu
	 * @param filmTitle tytul filmu
	 * @return lista aktorow dla filmu
	 */
	public List<Actor> getAllActorForFilm(String filmTitle) {
		for (Film film : db) {
			if(film.getTitle().equalsIgnoreCase(filmTitle)) {
				return film.getActor();
			}
		}
		return null;
	}
	
	/**
	 * Wyswietlenie filmow danego aktora
	 * @param actorSurname nazwisko aktora
	 * @return lista filmow aktora
	 */
	public List<Film> getAllFilmForActor(String actorSurname) {
		List<Film> result = new ArrayList<Film>();
		for (Film film : db) {
			for(Actor actor : film.getActor()) {
				if(actor.getName().equalsIgnoreCase(actorSurname)) {
					result.add(film);
				}
			}
		}
		return result;
	}
	
}
