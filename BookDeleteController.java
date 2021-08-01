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

public class BookDeleteController implements Initializable {

    @FXML
    Label title;

    @FXML
    Label author;

    @FXML
    Label price;
    
    @FXML
    Label quantity;

    Statement statement = null;

    Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        title.setText(BooksManagementMemory.title);
        author.setText(BooksManagementMemory.author);
        price.setText(String.valueOf(BooksManagementMemory.price));
        quantity.setText(String.valueOf(BooksManagementMemory.quantity));

    }

    @FXML
    public void deleteBook() {
        
        createStatement();

        try {
            String query = "delete from my_books where title = '" + BooksManagementMemory.title + "';";
            statement.executeUpdate(query);
            
            BooksManagementMemory.message = "Book Deleted Successfully";

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
