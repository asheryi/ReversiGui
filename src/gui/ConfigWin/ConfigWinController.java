package gui.ConfigWin;

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ConfigWinController extends GridPane implements Initializable {
    @FXML
    private ColorPicker firstColorCheckbox;
    @FXML
    private ColorPicker secColorCheckbox;
    @FXML
    private ComboBox boardSizeCheckbox;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private Label firstTimeErrorLabel;

    private String firstName = "Bob";
    private String secName = "Alice";
    private Scene menuScene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSettings();
    }

    /**
     * Saves the chosen settings in the file of settings.
     */
    public void saveSettings() {
        // Extracting data from the controllers.
        Color firstPlayerColor = firstColorCheckbox.getValue();
        Color secondPlayerColor = secColorCheckbox.getValue();
        Integer n = (Integer) boardSizeCheckbox.getValue();
        String firstNameStr = firstNameField.getText();
        if (firstNameStr.equals("")) {
            firstNameStr = this.firstName;
        } else {
            this.firstName = firstNameStr;
        }
        String secondNameStr = secondNameField.getText();
        if (secondNameStr.equals("")) {
            secondNameStr = secName;
        } else {
            this.secName = secondNameStr;
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
        this.firstTimeErrorLabel.setText("");

        exitSettingsWindow();
        setNamesToDisplay();
    }

    /**
     * Exits the windows if cancel / apply is pressed.
     */
    public void exitSettingsWindow() {
        ((Stage) firstColorCheckbox.getScene().getWindow()).setScene
                (menuScene);
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

    private void loadSettings() {
        try {
            Path path = Paths.get("settings.txt");
            if (Files.exists(path)) {
                List<String> settings = Files.readAllLines(path);
                firstColorCheckbox.setValue(Color.valueOf(settings.get(1)));
                secColorCheckbox.setValue(Color.valueOf(settings.get(3)));
                boardSizeCheckbox.setValue(Integer.valueOf(settings.get(4)));

                this.firstName = settings.get(0);
                this.secName = settings.get(2);
                setNamesToDisplay();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the names to the corresponding fields on the screen.
     */
    private void setNamesToDisplay() {
        firstNameField.setPromptText("Enter first player name here (current is: " + firstName + ")");
        secondNameField.setPromptText("Enter second player name here (current is: " + secName + ")");
        firstNameField.setText("");
        secondNameField.setText("");
    }

    public void setMenuScene(Scene menuScene_) {
        this.menuScene = menuScene_;
    }
}
