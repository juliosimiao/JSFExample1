package com.mycompany.libraryexample;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true) 
@RequestScoped 
public class NavigationController implements Serializable{
    
    Book book = new Book();
    
    public String addBook() {
        return "addBook";
    }
    
    public String searchBook() {
        return "searchBook";
    }
    
    public String bookDetails(String name) throws SQLException {
        return "bookDetails";
    }
    
}
