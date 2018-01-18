package gui.menuWin;

import gui.ConfigWin.ConfigWinController;
import gui.gameWin.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuWinController extends GridPane implements Initializable {
    @FXML
    private GridPane rootGrid;
    private FXMLLoader configLoader;
    private FXMLLoader gameLoader;
    private Scene configScene;
    private Scene gameScene;

    /*******
     Notice each fxml other then GraphicBoard.fxml is being read only once to save reading from files time.
     Thus , more fields are required ...
     ******/


    public void initialize(URL location, ResourceBundle resources) {
        configLoader = new FXMLLoader(this.getClass().getResource("/gui/ConfigWin/ConfigWin.fxml"));
        gameLoader = new FXMLLoader(this.getClass().getResource("/gui/gameWin/Game.fxml"));
    }

    @FXML
    /*
        Shows the settings window
     */
    public ConfigWinController showConfigWin() {

        Stage stage = (Stage) rootGrid.getScene().getWindow();
        try {
            ConfigWinController configWinController = configLoader.getController();
            if (configWinController == null) {
                configScene = new Scene(configLoader.load());
                configWinController = configLoader.getController();
            }
            stage.setTitle("Settings");
            configWinController.setMenuScene(stage.getScene());
            stage.setScene(configScene);
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
            int height = 600;
            int width = 600;

            Stage stage = (Stage) rootGrid.getScene().getWindow();
            try {
                GameController gameController = gameLoader.getController();
                if (gameController == null) {
                    gameScene = new Scene(gameLoader.load(), width, height);
                    gameController = gameLoader.getController();
                } else {
                    //irrelevant arguments
                    gameController.initialize(null, null);
                }
                stage.setTitle("Reversi");
                gameScene.getStylesheets().add(getClass().getResource("/gui/gameWin/game.css").toExternalForm());
                gameController.setMenuScene(stage.getScene());
                stage.setScene(gameScene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
