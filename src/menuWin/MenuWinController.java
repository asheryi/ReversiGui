package menuWin;
/**
 * Created by Brain on 10/01/2018.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuWinController extends GridPane {
    @FXML
    private Button configBtn;

    @FXML
    public void showConfigWin() {

        Stage stage = (Stage) configBtn.getScene().getWindow();
        try {
            stage.setTitle("Settings");
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/ConfigWin/ConfigWin.fxml"))));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
