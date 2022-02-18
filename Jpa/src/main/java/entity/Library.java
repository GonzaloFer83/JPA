package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Library implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String nif;
	private String name;
	private String owner;
	private String direction;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinTable(name="library_books",
				joinColumns= {@JoinColumn(name="fk_id_Library",referencedColumnName="nif")},
				inverseJoinColumns= {@JoinColumn(name="fk_id_book",referencedColumnName="id")})
	private List<Book> books;

	public Library() {
		
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

		getBooks().add(book);
		
		return book;
	}
	
	public Book removeBook(Book book) {
		books.remove(book);
		
		return book;
	}
	
	@Override
	public String toString() {
		return "Library [nif=" + nif + ", name=" + name + ", owner=" + owner + ", direction=" + direction +  "]";
	}
	
	
}
