package view;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.AuthorManager;
import model.BookManager;
import model.EditorialManager;
import model.LibraryManager;

public class Main {

	public static void main(String[] args) {
		
		String persistence="PruebaJPARelaciones";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence);
		
		AuthorManager authormanager= new AuthorManager(emf);
		
		authormanager.createAuthor("234234A", "Laura", "Anguera", "02/04/95");
		authormanager.createAuthor("000021M", "Carla", "de La La", "11/08/85");
		authormanager.createAuthor("834234A", "Rosario", "Raro", "24/09/00");

		
		EditorialManager editorialmanager= new EditorialManager(emf);
		
		editorialmanager.createEditorial("G456363", "Anaya", "C/Marin nº23");
		editorialmanager.createEditorial("F466660", "Planeta", "Av/España nº203");


		BookManager bookmanager=new BookManager(emf);

		bookmanager.createBook("UB44", "Nadie me hablo de ti", 14.20, editorialmanager.findEditorial("G456363"), authormanager.findAutor("234234A"));
		bookmanager.createBook("TR23", "Que te importa que te ame", 16.00, editorialmanager.findEditorial("G456363"), authormanager.findAutor("234234A"));
		bookmanager.createBook("YY66", "Cuando reamos ayer", 12.50, editorialmanager.findEditorial("G456363"), authormanager.findAutor("000021M"));
		bookmanager.createBook("IE77", "yo mate a Kennedy", 10.20, editorialmanager.findEditorial("F466660"), authormanager.findAutor("834234A"));
		bookmanager.createBook("PL09", "El juego de los crimenes perfectos", 20.00, editorialmanager.findEditorial("F466660"), authormanager.findAutor("834234A"));
		bookmanager.createBook("VC44", "El libro negro de las horas", 14.20, editorialmanager.findEditorial("F466660"), authormanager.findAutor("234234A"));
		bookmanager.createBook("BB62", "Bienamada", 17.10, editorialmanager.findEditorial("F466660"), authormanager.findAutor("234234A"));
		bookmanager.createBook("OO24", "Todos al infierno", 13.50, editorialmanager.findEditorial("G456363"), authormanager.findAutor("234234A"));
		bookmanager.createBook("NR46", "El corresponsal", 17.90, editorialmanager.findEditorial("G456363"), authormanager.findAutor("000021M"));
		//author.addBookAuthor(author.findAutor("234234A"), book.findBook("UB44"));         
		//editorial.addBookEditorial(editorial.findEditorial("G456363"), book.findBook("UB44"));
		
		
		LibraryManager librarymanager=new LibraryManager(emf);
		
		librarymanager.createLibrary("E025587", "Libreria Manolo", "Manolo", "C/ de Manolo nº 24");
		librarymanager.createLibrary("T333456", "Libreria Elisa", "Elisa", "C/ de Elisa nº 13");
		
		librarymanager.addBookLibrary(librarymanager.findLibrary("E025587"), bookmanager.findBook("UB44"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("E025587"), bookmanager.findBook("TR23"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("E025587"), bookmanager.findBook("YY66"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("E025587"), bookmanager.findBook("IE77"));

		librarymanager.addBookLibrary(librarymanager.findLibrary("T333456"), bookmanager.findBook("PL09"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("T333456"), bookmanager.findBook("VC44"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("T333456"), bookmanager.findBook("BB62"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("T333456"), bookmanager.findBook("NR46"));
		librarymanager.addBookLibrary(librarymanager.findLibrary("T333456"), bookmanager.findBook("OO24"));
		System.out.println("==========================================");
		List<Object[]>  result=bookmanager.getQuery1();
		System.out.println("Estos son todos los libros con sus editoriales y autores");
		for (Object[] row : result) {
			System.out.println("Libro[ " + row[0] + ", " + row[1] + "," + row[2] +"]"
							+ " Editorial[ " + row[3]+"]"
							+ " Autor[ " + row[4]+"]");
		}
		System.out.println("==========================================");
		result=authormanager.getQuery2();
		System.out.println("Estos son todos los autores con sus libros.");
		for (Object[] row : result) {
			System.out.println("Libro[ " + row[0] + ", " + row[1] + "," + row[2] +"]"
							+ " Editorial[ " + row[3]+"]"
							+ " Autor[ " + row[4]+"]");
		}
		System.out.println("==========================================");
		result=librarymanager.getQuery3();
		System.out.println("Estos son las librerias con sus libros");
		for (Object[] row : result) {
			System.out.println("Libreria[ " + row[0] + ", libro: " + row[1] + "]");
		}
		System.out.println("==========================================");
		result=bookmanager.getQuery4();
		System.out.println("Estos son los libros y sus librerias");
		for (Object[] row : result) {
			System.out.println("Libro[ " + row[0] + ", Libreria: " + row[1] + "]");
		}
		
		
		
	}

}
