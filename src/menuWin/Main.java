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
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 340, 400));
            primaryStage.show();
            //HBox root = (HBox) FXMLLoader.load(getClass().getResource("ConfigWinController.fxml"));
           /* Scene scene = new Scene(root, 520, 400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Maze game");
            primaryStage.setScene(scene);

            primaryStage.show();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}