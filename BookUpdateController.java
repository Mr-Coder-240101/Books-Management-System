package booksmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookUpdateController implements Initializable {
    
    @FXML
    private Label title;
    
    @FXML
    private Label oldPrice;
    
    @FXML
    private Label oldQuantity;
    
    @FXML
    private TextField newPrice;
    
    @FXML
    private TextField newQuantity;
    
    Statement statement = null;
    
    Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        title.setText(BooksManagementMemory.title);
        oldPrice.setText(String.valueOf(BooksManagementMemory.price));
        oldQuantity.setText(String.valueOf(BooksManagementMemory.quantity));
        
    }    
    
    @FXML
    public void updatePrice() {
        createStatement();
        
        try {
            int price = Integer.parseInt(newPrice.getText());
            int quantity = Integer.parseInt(newQuantity.getText());
            
            String query = "update my_books set price = " + price + "," + "quantity = " + quantity + " where title = '" + BooksManagementMemory.title + "';";
            statement.executeUpdate(query);
            
            BooksManagementMemory.message = "Book Updated Successfully";
            
            Parent root = FXMLLoader.load(getClass().getResource("MessageDisplay.fxml"));
            BooksManagementMemory.borderPane.setCenter(root);
            
        } catch (SQLException | IOException e) {
            
            System.out.println(e);
            
        } finally {
            
            closeStatement();
            
        }
        
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
