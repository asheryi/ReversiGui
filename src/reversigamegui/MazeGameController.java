package reversigamegui;

import ConfigWin.ConfigWinController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import graphicboard.GraphicBoard;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class MazeGameController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private VBox boardContainer;
    //private Board board;
    private GraphicBoard graphicBoard;
    private ConfigWinController configWinController;
    //public MazeGameController(Board board, Color firstPlayerColor, Color secPlayerColor) {
    //this.graphicBoard = new GraphicBoard(board, firstPlayerColor, secPlayerColor);
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readSettings();
        graphicBoard = new GraphicBoard();

        graphicBoard.setPrefWidth(400);
        graphicBoard.setPrefHeight(400);
        boardContainer.getChildren().add(0, graphicBoard);

        graphicBoard.draw();
        boardContainer.setOnKeyPressed(graphicBoard.getOnKeyPressed());
        boardContainer.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            graphicBoard.setPrefWidth(boardNewWidth);
            graphicBoard.draw();
        });
        boardContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            graphicBoard.setPrefHeight(newValue.doubleValue());
            graphicBoard.draw();
        });



    }

    private void readSettings() {
        try {
            List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}



