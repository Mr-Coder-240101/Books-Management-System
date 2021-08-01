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
import javafx.scene.control.TextField;

public class BookInsertController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField price;
    
    @FXML
    private TextField quantity;

    Statement statement = null;

    Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    public void insertBook() {

        createStatement();

        try {
            String titleString = this.title.getText();
            String authorString = this.author.getText();
            int priceInt = Integer.parseInt(this.price.getText());
            int quantityInt = Integer.parseInt(this.quantity.getText());

            String query = "insert into my_books values (" + "'" + titleString + "'" + ", " + "'" + authorString + "'" + ", " + priceInt + "," + quantityInt + ")" + ";";
            statement.executeUpdate(query);
            
            BooksManagementMemory.message = "Book Is Added Successfully";

            Parent root = FXMLLoader.load(getClass().getResource("MessageDisplay.fxml"));
            BooksManagementMemory.borderPane.setCenter(root);

        } catch (SQLException | IOException e) {

            try {
                BooksManagementMemory.message = "Book Is Aldready There";
                
                Parent root = FXMLLoader.load(getClass().getResource("MessageDisplay.fxml"));
                BooksManagementMemory.borderPane.setCenter(root);
                System.out.println(e);
            } catch (IOException ioe) {
                System.out.println(ioe);
            }

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
