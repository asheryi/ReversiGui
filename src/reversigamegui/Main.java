package reversigamegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MazeGame.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            MazeGameController mazeGameController = fxmlLoader.getController();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();
            mazeGameController.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}