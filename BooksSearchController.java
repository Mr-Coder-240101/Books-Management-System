package booksmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BooksSearchController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private Button search;

    Statement statement = null;

    Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void searchBook() {

        createStatement();

        try {
            String title = this.title.getText();

            String query = "select * from my_books where title = '" + title + "';";
            ResultSet resultSet = statement.executeQuery(query);

            Parent root;

            if (!resultSet.next()) {
                BooksManagementMemory.message = "No Book Found";
                
                root = FXMLLoader.load(getClass().getResource("MessageDisplay.fxml"));
                BooksManagementMemory.borderPane.setCenter(root);
            } else {
                BooksManagementMemory.title = title;
                BooksManagementMemory.author = resultSet.getString("author");
                BooksManagementMemory.price = resultSet.getInt("price");
                BooksManagementMemory.quantity = resultSet.getInt("quantity");

                if (BooksManagementMemory.isDeleteSelected) {
                    root = FXMLLoader.load(getClass().getResource("BookDelete.fxml"));
                    BooksManagementMemory.borderPane.setCenter(root);
                } else if (BooksManagementMemory.isUpdateSelected) {
                    root = FXMLLoader.load(getClass().getResource("BookUpdate.fxml"));
                    BooksManagementMemory.borderPane.setCenter(root);
                } else if (BooksManagementMemory.isSearchSelected) {
                    root = FXMLLoader.load(getClass().getResource("BookView.fxml"));
                    BooksManagementMemory.borderPane.setCenter(root);
                }
            }

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
