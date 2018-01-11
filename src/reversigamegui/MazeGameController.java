package reversigamegui;

import ConfigWin.ConfigWinController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import graphicboard.GraphicBoard;

import java.net.URL;
import java.util.ResourceBundle;

public class MazeGameController implements Initializable {
    @FXML
    private HBox root;
    //private Board board;
    private GraphicBoard graphicBoard;
    private ConfigWinController configWinController;
    //public MazeGameController(Board board, Color firstPlayerColor, Color secPlayerColor) {
    //this.graphicBoard = new GraphicBoard(board, firstPlayerColor, secPlayerColor);
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {


       /* graphicBoard.setPrefWidth(400);
        graphicBoard.setPrefHeight(400);
        root.getChildren().add(0, graphicBoard);
        graphicBoard.draw();
        root.setOnKeyPressed(graphicBoard.getOnKeyPressed());
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            graphicBoard.setPrefWidth(boardNewWidth);
            graphicBoard.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            graphicBoard.setPrefHeight(newValue.doubleValue());
            graphicBoard.draw();
        });*/

        fix();
    }

    public void fix() {
        configWinController = new ConfigWinController();
        root.getChildren().add(0, configWinController);
    }
}



