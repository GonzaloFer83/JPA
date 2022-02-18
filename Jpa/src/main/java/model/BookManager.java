package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;

import entity.Author;
import entity.Book;
import entity.Editorial;

public class BookManager {
	EntityManagerFactory emf = null;
	EntityManager em = null;
	public BookManager(EntityManagerFactory emf) {
		this.emf =emf;
	}
	
	public void createBook(String id, String title, double sale,Editorial editorial, Author author) {

		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setSale(sale);
		book.setAuthor(author);
		book.setEditorial(editorial);
	

		insertDatas(book);
		System.out.println("El Libro se ha dado de alta");
		

	}

	public Book findBook(String id) {
		em = emf.createEntityManager();
		Book book = em.find(Book.class, id);
		em.close();
		return book;
	}
	public void deleteBook(Book book) {
		em = emf.createEntityManager();
		if (book == null) {
			System.out.println("No existe ningun Libro con este Id");
		} else {
		    try {
		        em.getTransaction().begin();
		        em.remove(book);
		        em.getTransaction().commit();
		      } catch (Exception e) {
		        e.printStackTrace();
		      } finally {
		        em.close();
		      }
		}
	}
	

	public void modifyBook(String id, String title, double sale,Editorial editorial, Author author) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setSale(sale);
		book.setAuthor(author);
		book.setEditorial(editorial);
		author.addBook(book);
		editorial.addBook(book);
		
		insertDatas(book);
		System.out.println("El Libro se ha modificado");
	}
		
	public List<Book> books(){
		em= emf.createEntityManager();
		return em.createQuery("select b from Book b",Book.class).getResultList();
		
	}
	
	public void insertDatas(Book book) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		em.close();
		System.out.println("Conexion completada");
	}
	public List<Object[]> getQuery1() {
		
		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT l.id,l.title,l.sale,e.name,a.name FROM Book l "
				+ "INNER JOIN l.editorial e " + "INNER JOIN l.author a ");
		List<Object[]> result=query.getResultList();
		em.close();
		
		return result;
		
		
	}
	public List<Object[]> getQuery4() {

		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT l.title,lib.name FROM Book l INNER JOIN l.libreries lib");
		List<Object[]> result = query.getResultList();
		em.close();

		return result;

	}
}
