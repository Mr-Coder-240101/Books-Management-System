package booksmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BooksManagement extends Application {
    
    public static void main(String args[]) {
		Application.launch(args);
	}

    @Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("BooksManagement.fxml"));
		Scene scene = new Scene(root);
		Image image = new Image("booksmanagement/book_management.jpg");
		stage.getIcons().add(image);
		stage.setScene(scene);
		stage.setTitle("Books Management");
		stage.show();
	}
    
}
