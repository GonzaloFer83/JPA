package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editorial {
	
	@Id
	private String nif;
	private	String name;
	private String direction;
	
	
	@OneToMany(mappedBy="editorial", cascade=CascadeType.ALL)
	private List<Book> books;
	
	

	public Editorial() {
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	 
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public Book addBook(Book book) {
		books.add(book);
		book.setEditorial(this);
		
		return book;
		
		
	}
	public Book removeBook(Book book) {
		books.remove(book);
		book.setEditorial(null);
		
		return book;
	}
	

	@Override
	public String toString() {
		return "Editorial [nif=" + nif + ", name=" + name + ", direction=" + direction +  "]";
	}
	
}

