package menuWin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuWinController extends GridPane {
    @FXML
    private GridPane rootGrid;
    @FXML
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
    public void startGame() {

        Stage stage = (Stage) rootGrid.getScene().getWindow();
        try {
            stage.setTitle("Reversi");
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/reversigamegui/MazeGame.fxml")),600,600));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    public void exit() {
        Stage stage = (Stage) rootGrid.getScene().getWindow();
        stage.close();

    }


}
