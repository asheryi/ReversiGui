package menuWin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.misc.Resource;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Brain on 10/01/2018.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("menuWin.fxml"));
            primaryStage.setTitle("Welcome to the Reversi game");
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("menuWin.fxml"))));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}