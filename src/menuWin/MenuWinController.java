package menuWin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuWinController extends GridPane {
    @FXML
    private GridPane rootGrid;

    @FXML
    /*
        Shows the settings window.
     */
    public void showConfigWin() {

        Stage stage = (Stage) rootGrid.getScene().getWindow();
        try {
            stage.setTitle("Settings");
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/ConfigWin/ConfigWin.fxml"))));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("AFTER SHOW");

    }

    @FXML
    /*
      Starts the game (and openning the matching scene)
     */
    public void startGame() {

        Stage stage = (Stage) rootGrid.getScene().getWindow();
        try {
            stage.setTitle("Reversi");
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/reversigamegui/Game.fxml")), 600, 600);
            scene.getStylesheets().add(getClass().getResource("/reversigamegui/game.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
