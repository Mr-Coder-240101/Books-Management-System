package booksmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MessageDisplayController implements Initializable {
    
    @FXML
    Label myLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myLabel.setText(BooksManagementMemory.message);
        
    }     
    
}
