package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Book implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String title;
	private double sale;
	
	@ManyToOne//(cascade=CascadeType.PERSIST)
	@JoinColumn(name="fk_Dni_author",referencedColumnName="dni")
	private Author author;
	
	@ManyToOne//(cascade=CascadeType.PERSIST)
	@JoinColumn(name="fk_Nif_Editorial",referencedColumnName="nif")
	private Editorial editorial;
	
	@ManyToMany(mappedBy="books",cascade=CascadeType.PERSIST)
	private List<Library> libreries;
	
	
	
	public Book() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {

		this.author = author;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {

		this.editorial = editorial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public List<Library> getLibreries() {
		return libreries;
	}

	public void setLibreries(List<Library> libreries) {
		this.libreries = libreries;
	}
	public void addLibrary(Library library) {
		libreries.add(library);
	}
	public void remove(Library library) {
		libreries.remove(library);
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", sale=" + sale + ", author=" + author + ", editorial="
				+ editorial + "]\n";
	}

	
	
	
	
	
}
