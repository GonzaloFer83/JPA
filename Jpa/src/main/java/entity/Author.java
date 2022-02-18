package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity


public class Author implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	private String dni;
	private String name;
	private String surnames;
	private String date;
	
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	private List<Book> books;
	 
	

	public Author() {
	
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	public Book addBook(Book book) {
		getBooks().add(book);
		book.setAuthor(this);
		
		return book;
		
		
	}
	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setAuthor(null);
		
		return book;
	}
	
	@Override
	public String toString() {
		return "Author [dni=" + dni + ", name=" + name + ", surnames=" + surnames + ", date=" + date +  "]\n";
	}

}
