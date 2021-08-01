package booksmanagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class BooksViewController implements Initializable {
    
    @FXML
    Label titleLabel;
    
    @FXML
    Label authorLabel;
    
    @FXML
    Label priceLabel;
    
    @FXML
    Label quantityLabel;
    
    Statement statement = null;
    
    Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StringBuffer titleBuffer = new StringBuffer();
        titleBuffer.append("Title");
        
        StringBuffer authorBuffer = new StringBuffer();
        authorBuffer.append("Author");
        
        StringBuffer priceBuffer = new StringBuffer();
        priceBuffer.append("Price");
        
        StringBuffer quantityBuffer = new StringBuffer();
        quantityBuffer.append("Quantity");
        
        createStatement();
        
        try {            
            String query = "select * from my_books;";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                
                titleBuffer.append("\n").append(title);
                authorBuffer.append("\n").append(author);
                priceBuffer.append("\n").append(price);
                quantityBuffer.append("\n").append(quantity);
            }
            
        } catch (SQLException e) {        
            System.out.println(e);
            
        } finally {
            
            closeStatement();
            
        }
        
        titleLabel.setText(titleBuffer.toString());
        authorLabel.setText(authorBuffer.toString());
        priceLabel.setText(priceBuffer.toString());
        quantityLabel.setText(quantityBuffer.toString());
        
    }  
    
    private void createStatement() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = constants.Constants.JDBC_URL;
            String username = constants.Constants.JDBC_USERNAME;
            String password = constants.Constants.JDBC_PASSWORD;
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
    }

    private void closeStatement() {
        
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
}
