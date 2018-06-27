package com.mycompany.libraryexample.DAO;

import com.mycompany.libraryexample.Book;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    
    private Connection con;
    
    public DatabaseConnection(){}
    
    public Connection getConnection() throws SQLException {
        if(con != null) {
            return con;
        }
        
        con = (Connection) DriverManager.getConnection(url, user, password);
        return con;
    }
    
    public void addBook(Book book) throws SQLException {
        
        Statement st = getConnection().createStatement();
        st.execute("INSERT INTO Book (name, isBooked) VALUES ('" 
                + book.getName() + "'," + book.getIsBooked() + ")");
    }
    
    public List<Book> allBooks() throws SQLException {
        
        List<Book> books = new ArrayList<>();
        
        Statement stmt = null;
        String query = "select id, name, isBooked from Book";
        
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int booked = rs.getInt("isBooked");
                boolean isBooked;
                if(booked == 1) {
                    isBooked = true;
                } else {
                    isBooked = false;
                }
                books.add(new Book(id,name,isBooked));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }finally {
            if (stmt != null) { 
                stmt.close();
            }
        }
        
        return books;
    }
    
    public Book findBook(String nameOfTheBook) throws SQLException {
        
        Book book = null;
        
        Statement stmt = null;
        String query = "select id, name, isBooked from Book "
                + "where name = '" + nameOfTheBook + "'";
        
        System.out.println(query);
        
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int booked = rs.getInt("isBooked");
                boolean isBooked;
                if(booked == 1) {
                    isBooked = true;
                } else {
                    isBooked = false;
                }
                book = new Book(id,name,isBooked);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }finally {
            if (stmt != null) { 
                stmt.close();
            }
        }
        
        return book;
    }
    
    public int findIdBook(String nameOfTheBook) throws SQLException {
        
        int id = -1;
        
        Statement stmt = null;
        String query = "select id from Book "
                + " where name = '" + nameOfTheBook + "'";
        
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }finally {
            if (stmt != null) { 
                stmt.close();
            }
        }
        
        return id;
    }
    
    public void updateBook(String nameBook) throws SQLException {
        Book b = null;
        int bookId = findIdBook(nameBook);
        if(bookId != -1) {
           
            String query = "UPDATE Book\n" +
                "SET isBooked = " +
                "CASE WHEN isBooked = 1 THEN 0 " +
                "ELSE 1 END " +
                "where id = ?";
            
            try {
                PreparedStatement st = getConnection().prepareStatement(query);
                st.setInt(1, bookId);
                st.executeUpdate();
                st.close();
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        
        }    
    }
    
}
