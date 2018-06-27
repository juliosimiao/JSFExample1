package com.mycompany.libraryexample;

import com.mycompany.libraryexample.DAO.DatabaseConnection;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/*import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/

@ManagedBean(name = "book", eager = true)
@SessionScoped
//@Entity
//@Table(name = "Book")
public class Book implements Serializable{
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@Column(name = "id")
    private Integer id;
    //@Column(name = "isBooked")
    private Boolean isBooked;
    
    private static final long serialVersionUID = 1L;
    
    private List<Book> allbooks;
    private Book findBook;

    public void setAllbooks(List<Book> allbooks) {
        this.allbooks = allbooks;
    }

    public Book getFindBook() {
        return findBook;
    }
    
    //@Column(name = "name")
    private String name;
    
    public Book(String name, boolean isBooked){
        super();
        this.name = name;
        this.isBooked = isBooked;
    }
    
    public Book(int id, String name, boolean isBooked){
        super();
        this.id = id;
        this.name = name;
        this.isBooked = isBooked;
    }
    
    public Book(){
        try {
            allbooks = getAllbooks();
            findBook = getFindBook();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setAllbooks(ArrayList<Book> allbooks) {
       this.allbooks = allbooks;
    }

    public List<Book> getAllbooks() throws SQLException {
       return allBooks();
    }

    
    public void addBook(String name) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        Book book = new Book(name,false);
        dc.addBook(book);        
    }
    
    public List<Book> allBooks() throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        allbooks = dc.allBooks();
        
        return allbooks;        
    }
    
    public Book searchBook(String nameBook) throws SQLException{
        DatabaseConnection dc = new DatabaseConnection();
        findBook = dc.findBook(nameBook);       
        
        return findBook;
    }
    
    public void update(String nameBook) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        dc.updateBook(nameBook);
    }
    
    public String successfulMessage() {
        return "The book " + name + "was successful booked";
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }
    
    
/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.libraryexample.Book[ id=" + id + " ]";
    }
  */  
}
