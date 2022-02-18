package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Book;
import entity.Editorial;

public class EditorialManager {
	EntityManagerFactory emf = null;
	EntityManager em = null;
	public EditorialManager(EntityManagerFactory emf) {
		this.emf =emf;
	}
	
	public void createEditorial(String nif, String name, String direction) {
		Editorial editorial = new Editorial();
		editorial.setNif(nif);
		editorial.setName(name);
		editorial.setDirection(direction);
		

		insertDatas(editorial);
		System.out.println("La Editorial se ha dado de alta");

	}

	public Editorial findEditorial(String nif) {
		em = emf.createEntityManager();
		Editorial editorial = em.find(Editorial.class, nif);
		em.close();
		return editorial;
	}
	public void deleteEditorial(Editorial editorial) {
		em = emf.createEntityManager();
		if (editorial == null) {
			System.out.println("No existe ninguna Editorial con este NIF");
		} else {
		    try {
		        em.getTransaction().begin();
		        em.remove(editorial);
		        em.getTransaction().commit();
		      } catch (Exception e) {
		        e.printStackTrace();
		      } finally {
		        em.close();
		      }
		}
	}
	
	public void deleteBookEditorial(Editorial editorial,Book book) {
		editorial.removeBook(book);
		insertDatas(editorial);
	}
	public void addBookEditorial(Editorial editorial,Book book) {
		editorial.addBook(book);
		insertDatas(editorial);
	}
	
	public void modifyEditorial(String nif, String name, String direction) {
		Editorial editorial = new Editorial();
		editorial.setNif(nif);
		editorial.setName(name);
		editorial.setDirection(direction);
		

		insertDatas(editorial);
		System.out.println("La Editorial se ha modificado");
	}
		
	public List<Editorial> editorials(){
		em= emf.createEntityManager();
		return em.createQuery("select e from Editorial e",Editorial.class).getResultList();
		
	}
	
	public void insertDatas(Editorial editorial) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(editorial);
		em.getTransaction().commit();
		em.close();
		System.out.println("Conexion completada");
	}
}
