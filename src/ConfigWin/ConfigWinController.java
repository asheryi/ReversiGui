package ConfigWin;

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private Label firstTimeErrorLabel;

    private final String defaultFirstName = "Bob";
    private final String defaultSecName = "Alice";

    /**
     * Saves the chosen settings in the file of settings.
     */
    public void saveSettings() {
        // Extracting data from the controllers.
        Color firstPlayerColor = firstColorCheckbox.getValue();
        Color secondPlayerColor = secColorCheckbox.getValue();
        Integer n = (Integer) boardSizeCheckbox.getValue();
        String firstNameStr = firstName.getText();
        if (firstNameStr.equals("")) {
            firstNameStr = defaultFirstName;
        }
        String secondNameStr = secondName.getText();
        if (secondNameStr.equals("")) {
            secondNameStr = defaultSecName;
        }

        // Constructing the coded data as string .
        String data = firstNameStr + "\n" + firstPlayerColor + "\n"
                + secondNameStr + "\n" + secondPlayerColor + "\n" + n;

        // Writing.
        Path file = Paths.get("settings.txt");
        try {
            Files.write(file, Collections.singleton(data), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        exitSettingsWindow();
    }

    /**
     * Exits the windows if cancel / apply is pressed.
     */
    public void exitSettingsWindow() {
        try {
            Scene oldScene = new Scene(FXMLLoader.load(this.getClass().getResource("/menuWin/menuWin.fxml")));
            oldScene.getStylesheets().add(getClass().getResource("/menuWin/application.css").toExternalForm());
            ((Stage) firstColorCheckbox.getScene().getWindow()).setScene
                    (oldScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSettingsFileRequired(String error) {
        firstTimeErrorLabel.setText(error);
    }

    @FXML
    public void firstPlayerColorChanged() {
        playerColorChanged(0);
    }

    @FXML
    public void secPlayerColorChanged() {
        playerColorChanged(1);
    }

    /**
     * Checks if the players colors are the same , and doesn't allow it.
     *
     * @param player
     */
    private void playerColorChanged(int player) {
        if (firstColorCheckbox.getValue().equals(secColorCheckbox.getValue())) {
            Color defaultColor = Color.valueOf("Black");
            if (defaultColor.equals(firstColorCheckbox.getValue()))
                defaultColor = Color.valueOf("White");
            if (player == 0)
                firstColorCheckbox.setValue(defaultColor);
            else
                secColorCheckbox.setValue(defaultColor);

        }
    }
}
