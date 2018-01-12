package ConfigWin;/**
 * Created by Brain on 1/10/2018.
 */

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class ConfigWinController extends GridPane {
    @FXML
    private ColorPicker firstColorCheckbox;
    @FXML
    private ColorPicker secColorCheckbox;
    @FXML
    private ComboBox boardSizeCheckbox;

    private Scene lastScene;

    public void saveSettings() {
        Color firstPlayerColor = firstColorCheckbox.getValue();
        Color secondPlayerColor = secColorCheckbox.getValue();
        Integer n = (Integer) boardSizeCheckbox.getValue();

        String data = firstPlayerColor + "\n" + secondPlayerColor + "\n" + n;

        Path file = Paths.get("settings.txt");
        try {
            Files.write(file, Collections.singleton(data), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        exitSettingsWindow();
    }

    public void exitSettingsWindow() {
        try {
            ((Stage) firstColorCheckbox.getScene().getWindow()).setScene
                    (new Scene(FXMLLoader.load(this.getClass().getResource("/menuWin/menuWin.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
