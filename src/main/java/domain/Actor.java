/**
 * 
 * @author Dominik
 * @version 1.0
 * 
 * Pakiet zawiera dwie klasy i stanowi domenę 
 * - model danych dla serwisu filmowego
 * @see Film
 * @see Actor
 * 
 */
package domain;

import java.util.List;

/**
 *  
 * Klasa jest modelem danych opisującym aktorów.
 * W pakiecie 
 * @see domain 
 * występuje też druga klasa modelu danych
 * @see domain.Film
 * 
 */
public class Actor {
	
	private int id;
	private String name;
	private String surname;
	private List<Film> film;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Film> getFilm() {
		return film;
	}
	public void addFilm(Film film) {
		this.film.add(film);
	}
	
	
}
