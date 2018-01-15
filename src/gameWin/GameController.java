package gameWin;

import gameLogic.*;
import gameLogic.Cell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import graphicboard.GraphicBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class GameController implements Initializable, Display {
    @FXML
    private GridPane boardContainer_;
    @FXML
    private Pane currPlayerImg;
    @FXML
    private Pane firstPlayerImg;
    @FXML
    private Pane secPlayerImg;
    private Pane[] playersImg;
    @FXML
    private Label firstPlayerScore;
    @FXML
    private Label secPlayerScore;
    @FXML
    private Label firstPlayerName;
    @FXML
    private Label secPlayerName;

    private GraphicBoard graphicBoard;
    private Color[] playersColor;
    private String[] playersName;
    private int boardSize;
    private Game game;
    private int[] scores;
    private LinearGradient[] PlayersLG;
    public javafx.scene.control.Label statusLabel;

    private javax.management.timer.Timer timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readSettings();
        playersImg = new Pane[2];
        playersImg[0] = firstPlayerImg;
        playersImg[1] = secPlayerImg;
        PlayersLG = new LinearGradient[2];
        Stop[] stops = new Stop[]{new Stop(0, playersColor[0]), new Stop(1, Color.BLACK)};
        PlayersLG[0] = new LinearGradient(0, 0, 2, 1, true, CycleMethod.REFLECT, stops);
        stops = new Stop[]{new Stop(0, playersColor[1]), new Stop(1, Color.BLACK)};
        PlayersLG[1] = new LinearGradient(0, 0, 2, 1, true, CycleMethod.REFLECT, stops);


        Board board = new Board(boardSize, boardSize, new ArrayList<>(), new ArrayList<>());
        game = new Game(boardSize, boardSize, this, this, board);

        this.scores = new int[2];
        scores[0] = scores[1] = 0;
        // Initial scores :
        for (int i = 1; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                if (board.getCellValue(i, j) == TypesOf.Color.black) {
                    scores[0]++;
                } else if (board.getCellValue(i, j) == TypesOf.Color.white) {
                    scores[1]++;
                }
            }
        }
        int graphicBoardLength = 400;
        // Creating the graphic board
        graphicBoard = new GraphicBoard(playersColor);
        // Sets it's size
        graphicBoard.setPrefWidth(graphicBoardLength);
        graphicBoard.setPrefHeight(graphicBoardLength);
        boardContainer_.getChildren().add(0, graphicBoard);
        boardContainer_.setOnMouseClicked(event -> {
            int height = (int) boardContainer_.getPrefHeight();
            int width = (int) boardContainer_.getPrefWidth();
            int cellHeight = height / board.getRows();
            int cellWidth = width / board.getColumns();
            int squreSide = cellHeight >= cellWidth ? cellWidth : cellHeight;
            squreSide -= 1;
            Integer x = (int) Math.floor(event.getSceneX() / squreSide) + 1;
            Integer y = (int) Math.floor(event.getSceneY() / squreSide) + 1;
            Cell c = new Cell(y, x);

            // status get's reset
            statusLabel.setText("");

            // makes a move
            game.makeMove(c);
        });
        boardContainer_.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            graphicBoard.setPrefWidth(boardNewWidth);
            boardContainer_.setPrefWidth(boardNewWidth);
            graphicBoard.draw(board, game.getCurrPlayerValidMoves());
            notification();
        });
        boardContainer_.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            graphicBoard.setPrefHeight(boardNewWidth);
            boardContainer_.setPrefHeight(boardNewWidth);
            graphicBoard.draw(board, game.getCurrPlayerValidMoves());
            notification();
        });


    }

    /**
     * General controllers update .(according to the current player)
     */
    private void notification() {
        int currPlayer = game.getCurrPlayerIndex();

        Ellipse currPlayerDisk = new Ellipse(40, 40, 20, 10);
        currPlayerDisk.setFill(PlayersLG[currPlayer]);

        currPlayerImg.getChildren().clear();
        currPlayerImg.getChildren().add(0, currPlayerDisk);

        Ellipse firstPlayerDisk = new Ellipse(40, 40, 20, 10);
        firstPlayerDisk.setFill(PlayersLG[0]);

        playersImg[currPlayer].getChildren().clear();
        playersImg[currPlayer].getChildren().add(0, firstPlayerDisk);

        Ellipse secPlayerDisk = new Ellipse(40, 40, 20, 10);
        secPlayerDisk.setFill(PlayersLG[1]);

        playersImg[1].getChildren().clear();
        playersImg[1].getChildren().add(0, secPlayerDisk);


        firstPlayerScore.setText(String.valueOf(scores[0]));
        secPlayerScore.setText(String.valueOf(scores[1]));

    }

    /**
     * Reading the settings from the file , thus extracting the data from it.
     */
    private void readSettings() {
        try {
            playersColor = new Color[2];
            playersName = new String[2];
            List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
            playersName[0] = settings.get(0);
            playersColor[0] = Color.valueOf(settings.get(1));
            playersName[1] = settings.get(2);
            firstPlayerName.setText(playersName[0] + ":");
            secPlayerName.setText(playersName[1] + ":");
            playersColor[1] = Color.valueOf(settings.get(3));
            boardSize = Integer.valueOf(settings.get(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***********************************************************************************************************
     **************************************DISPLAY INTERFACE METHODS********************************************
     ***********************************************************************************************************/

    @Override
    public void show(Board board, List<gameLogic.Path> moves, int currPlayer, boolean passTurn, int blacks, int whites) {
        graphicBoard.draw(board, game.getCurrPlayerValidMoves());
        scores[0] = blacks;
        scores[1] = whites;
        notification();

        if (passTurn) {
            statusLabel.setText("No moves are possible for " + playersName[1 - currPlayer] + ", turn passed to " + playersName[currPlayer]);
        }
    }

    @Override
    public void showError(TypesOf.Error errorType) {
        if (errorType == TypesOf.Error.notValidMove) {
            statusLabel.setText("Not Valid Move , please click on one of the marked cells only !");
        }
    }

    @Override
    public void showEndGameStatus(TypesOf.GameStatus gameStatus) {
        if (gameStatus == TypesOf.GameStatus.blackWon) {
            statusLabel.setText(playersName[0] + " won !");
        } else if (gameStatus == TypesOf.GameStatus.whiteWon) {
            statusLabel.setText(playersName[1] + " won !");
        } else if (gameStatus == TypesOf.GameStatus.tie) {
            statusLabel.setText("It's a tie , try again !");
        }

        // No more pressing is allowed !
        boardContainer_.setOnMouseClicked(event -> {
        });
    }

    // In the current implementation of the Display interface there is no need for this func
    @Override
    public void showMoveDone(Cell cell, TypesOf.Color playerColor) {

    }

    /**
     * Returns to the main menu.
     */
    @FXML
    public void Menu() {
        try {
            Scene oldScene = new Scene(FXMLLoader.load(this.getClass().getResource("/menuWin/menuWin.fxml")));
            oldScene.getStylesheets().add(getClass().getResource("/menuWin/application.css").toExternalForm());
            ((Stage) boardContainer_.getScene().getWindow()).setScene
                    (oldScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



