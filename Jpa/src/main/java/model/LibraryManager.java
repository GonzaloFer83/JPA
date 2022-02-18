package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entity.Book;
import entity.Library;

public class LibraryManager {
	EntityManagerFactory emf = null;
	EntityManager em = null;

	public LibraryManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void createLibrary(String nif, String name, String owner, String direction) {
		Library library = new Library();
		library.setNif(nif);
		library.setName(name);
		library.setOwner(owner);
		library.setDirection(direction);

		insertDatas(library);
		System.out.println("La Libreria se ha dado de alta");

	}

	public Library findLibrary(String nif) {
		em = emf.createEntityManager();
		Library library = em.find(Library.class, nif);
		em.close();
		return library;
	}

	public void deleteLibrary(Library library) {
		em = emf.createEntityManager();
		if (library == null) {
			System.out.println("No existe ninguna Libreria con este NIF");
		} else {
			try {
				em.getTransaction().begin();
				em.remove(library);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
			}
		}
	}

	public void deleteBookLibrary(Library library, Book book) {
		library.removeBook(book);
		insertDatas(library);
	}

	public void addBookLibrary(Library library, Book book) {

		List<Book> books = new ArrayList();
		books = library.getBooks();
		books.add(book);
		library.setBooks(books);

		updateDatas(library);
	}

	public void modifyAuthor(String nif, String name, String owner, String direction) {
		Library library = new Library();
		library.setNif(nif);
		library.setName(name);
		library.setOwner(owner);
		library.setDirection(direction);

		insertDatas(library);
		System.out.println("La libreria se ha modificado");
	}

	public List<Library> bookstores() {
		em = emf.createEntityManager();
		return em.createQuery("select l from Library l", Library.class).getResultList();

	}

	public void insertDatas(Library library) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		System.out.println("estoy en este paso");

		em.persist(library);
		em.getTransaction().commit();
		em.close();
		System.out.println("Conexion completada");
	}

	public void updateDatas(Library library) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		System.out.println("estoy en este paso");

		em.merge(library);
		em.getTransaction().commit();
		em.close();
		System.out.println("Conexion completada");
	}

	public List<Object[]> getQuery3() {

		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT lib.name,l.title FROM Library lib INNER JOIN lib.books l");
		List<Object[]> result = query.getResultList();
		em.close();

		return result;

	}
}
