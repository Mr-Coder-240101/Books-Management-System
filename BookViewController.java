package booksmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class BookViewController implements Initializable {
    
    @FXML
    Label title;

    @FXML
    Label author;

    @FXML
    Label price;
    
    @FXML
    Label quantity;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        title.setText(BooksManagementMemory.title);
        author.setText(BooksManagementMemory.author);
        price.setText(String.valueOf(BooksManagementMemory.price));
        quantity.setText(String.valueOf(BooksManagementMemory.quantity));
        
    }    
    
    @FXML
    public void goToHome() {
        
        try {
            BooksManagementMemory.message = "Select Your Option !";

            Parent root = FXMLLoader.load(getClass().getResource("MessageDisplay.fxml"));
            BooksManagementMemory.borderPane.setCenter(root);
            
        } catch(IOException e) {
            
            System.out.println(e);
            
        }
        
    }
    
}
