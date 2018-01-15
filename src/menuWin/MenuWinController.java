package menuWin;

import ConfigWin.ConfigWinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

public class MenuWinController extends GridPane {
    @FXML
    private GridPane rootGrid;

    @FXML
    /*
        Shows the settings window.
     */
    public ConfigWinController showConfigWin() {

        Stage stage = (Stage) rootGrid.getScene().getWindow();
        try {
            stage.setTitle("Settings");
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/ConfigWin/ConfigWin.fxml"));
            Parent p = fxmlLoader.load();
            ConfigWinController configWinController = fxmlLoader.getController();
            stage.setScene(new Scene(p));
            stage.show();
            return configWinController;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

    @FXML
    /*
      Starts the game (and opening the matching scene)
     */
    public void startGame() {
        File settingsFile = new File("settings.txt");
        if (!settingsFile.exists()) {
            ConfigWinController configWinController = showConfigWin();
            configWinController.setSettingsFileRequired("Game cannot start before any \nsettings are saved (press apply)");
        } else {
            Stage stage = (Stage) rootGrid.getScene().getWindow();
            try {
                stage.setTitle("Reversi");
                Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/gameWin/Game.fxml")), 600, 600);
                scene.getStylesheets().add(getClass().getResource("/gameWin/game.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
