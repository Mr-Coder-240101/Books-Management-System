package booksmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class BooksManagementController implements Initializable {
    
    @FXML
    BorderPane borderPane;
    
    @FXML 
    Button search;
    
    @FXML 
    Button insert;
    
    @FXML 
    Button update;
    
    @FXML 
    Button delete;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooksManagementMemory.borderPane = borderPane;
    }
    
    @FXML
    public void showSearchScreen(MouseEvent mouseEvent) {
        BooksManagementMemory.isSearchSelected = true;
        BooksManagementMemory.isUpdateSelected = false;
        BooksManagementMemory.isDeleteSelected = false;
        
        setCenterToBooksSearch();
    }
    
    @FXML
    public void showInsertScreen(MouseEvent mouseEvent) {
        
        BooksManagementMemory.isSearchSelected = false;
        BooksManagementMemory.isUpdateSelected = false;
        BooksManagementMemory.isDeleteSelected = false;
        
        setCenterToBookInsert();
        
    }
    
    @FXML
    public void showUpdateScreen(MouseEvent mouseEvent) {
        
        BooksManagementMemory.isSearchSelected = false;
        BooksManagementMemory.isUpdateSelected = true;
        BooksManagementMemory.isDeleteSelected = false;
        
        setCenterToBooksSearch();
               
    }
    
    @FXML
    public void showDeleteScreen(MouseEvent mouseEvent) {
        
        BooksManagementMemory.isSearchSelected = false;
        BooksManagementMemory.isUpdateSelected = false;
        BooksManagementMemory.isDeleteSelected = true;
          
        setCenterToBooksSearch();
        
    }
    
    @FXML
    public void showViewScreen(MouseEvent mouseEvent) {
        
        BooksManagementMemory.isSearchSelected = false;
        BooksManagementMemory.isUpdateSelected = false;
        BooksManagementMemory.isDeleteSelected = false;
        
        setCenterToBooksView();
        
    }
    
    private void setCenterToBooksSearch() {
        
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("BooksSearch.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        borderPane.setCenter(root);
        
    }
    
    private void setCenterToBookInsert() {
        
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("BookInsert.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        borderPane.setCenter(root);
        
    }
    
    private void setCenterToBooksView() {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("BooksView.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        borderPane.setCenter(root);
        
    }
    
    
}
