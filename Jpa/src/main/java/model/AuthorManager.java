package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Author;
import entity.Book;


public class AuthorManager {
	EntityManagerFactory emf = null;
	EntityManager em = null;
	public AuthorManager(EntityManagerFactory emf) {
		this.emf =emf;
	}
	
	public void createAuthor(String dni, String name, String surnames, String date) {
		Author author = new Author();
		author.setDni(dni);
		author.setName(name);
		author.setSurnames(surnames);
		author.setDate(date);

		insertDatas(author);
		System.out.println("El Autor se ha dado de alta");

	}

	public Author findAutor(String dni) {
		em = emf.createEntityManager();
		Author author = em.find(Author.class, dni);
		em.close();
		return author;
	}
	public void deleteAuthor(Author author) {
		em = emf.createEntityManager();
		if (author == null) {
			System.out.println("No existe ningun Author con este DNI");
		} else {
		    try {
		        em.getTransaction().begin();
		        em.remove(author);
		        em.getTransaction().commit();
		      } catch (Exception e) {
		        e.printStackTrace();
		      } finally {
		        em.close();
		      }
		}
	}
	
	public void deleteBookAuthor(Author author,Book book) {
		author.removeBook(book);
		insertDatas(author);
	}
	public void addBookAuthor(Author author,Book book) {
		author.addBook(book);
		insertDatas(author);
	}
	
	public void modifyAuthor(String dni, String name, String surnames, String date) {
		Author author = findAutor(dni);
		author.setName(name);
		author.setSurnames(surnames);
		author.setDate(date);

		insertDatas(author);
		System.out.println("El Autor se ha modificado");
	}
		
	public List<Author> authors(){
		em= emf.createEntityManager();
		return em.createQuery("select a from Author a",Author.class).getResultList();
		
	}
	
	public void insertDatas(Author author) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(author);
		em.getTransaction().commit();
		em.close();
		System.out.println("Conexion completada");
	}

	public List<Object[]> getQuery2() {

		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT a.name,a.surnames,l.id,l.title,l.sale FROM Book l INNER JOIN l.author a");
		List<Object[]> result = query.getResultList();
		em.close();

		return result;

	}
}
