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
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("/ConfigWin/ConfigWin.fxml"));
            stage.setTitle("Hello World");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
