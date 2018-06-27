package com.mycompany.libraryexample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class BookController {
    
    @PersistenceContext
    private EntityManagerFactory em;
    
    private String path = "com.mycompany_LibraryExample_war_1.0-SNAPSHOTPU";
    
    public BookController(){}
    
    private EntityManagerFactory getEntityManager() {
        em = Persistence.createEntityManagerFactory(path);
        return em;
    }
    
    public void addBook(String name){
        Book book = new Book(name,false);
        System.out.println(book.getName() + " - " + book.getIsBooked());
        //getEntityManager().createEntityManager().getTransaction().begin();
        getEntityManager().createEntityManager().persist(book);
        getEntityManager().createEntityManager().flush();
        //getEntityManager().createEntityManager().getTransaction().commit();
    }
    
}
