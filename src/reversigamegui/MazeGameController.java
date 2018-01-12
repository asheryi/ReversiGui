package reversigamegui;

import ConfigWin.ConfigWinController;
import Logic.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import graphicboard.GraphicBoard;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MazeGameController implements Initializable, Display {
    @FXML
    private HBox boardContainer_;
    //private Board board;
    private GraphicBoard graphicBoard;
    private ConfigWinController configWinController;
    private Color firstPlayerColor;
    private Color secPlayerColor;
    private Integer boardSize;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readSettings();
        Board board = new Board(boardSize, boardSize, new ArrayList<>(), new ArrayList<>());

        game = new Game(boardSize, boardSize, this, this, board);

        graphicBoard = new GraphicBoard(firstPlayerColor, secPlayerColor);

        graphicBoard.setPrefWidth(400);
        graphicBoard.setPrefHeight(400);
        boardContainer_.getChildren().add(0, graphicBoard);

        boardContainer_.setOnKeyPressed(graphicBoard.getOnKeyPressed());
        boardContainer_.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override public void handle(javafx.scene.input.MouseEvent event) {
                int  height=(int)boardContainer_.getPrefHeight();
                int width = (int)boardContainer_.getPrefWidth();
                int cellHeight = height / board.getRows();
                int cellWidth = width / board.getColumns();
                int squreSide = cellHeight >= cellWidth ? cellWidth : cellHeight;
                Integer x=(int)Math.floor(event.getX()/squreSide)+1;
                Integer y=(int)Math.floor(event.getY()/squreSide)+1;
                Cell c=new Cell(y,x);
                game.makeMove(c);
                System.out.println("(x: "       +   x    + ", y: "       +y);
            }
        });
      //  boardContainer_.setOnMouseClicked(graphicBoard.getOnMouseClicked());
        boardContainer_.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            graphicBoard.setPrefWidth(boardNewWidth);
            boardContainer_.setPrefWidth(boardNewWidth);
            graphicBoard.draw(board,game.getValidMoves());
        });
        boardContainer_.heightProperty().addListener((observable, oldValue, newValue) -> {
            graphicBoard.setPrefHeight(newValue.doubleValue());
            boardContainer_.setPrefHeight(newValue.doubleValue());
            graphicBoard.draw(board,game.getValidMoves());
        });


    }

    public void startGame(){
        //game.start();
    }

    private void readSettings() {
        try {
            List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
            firstPlayerColor = Color.valueOf(settings.get(0));
            secPlayerColor = Color.valueOf(settings.get(1));
            boardSize = Integer.valueOf(settings.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void show(Board board, List<Logic.Path> moves, TypesOf.Color currPlayerColor, boolean passTurn, int blacks, int whites) {
        graphicBoard.draw(board,game.getValidMoves());
    }

    @Override
    public void showError(TypesOf.Error errorType) {

    }

    @Override
    public void showEndGameStatus(TypesOf.GameStatus gameStatus) {

    }

    @Override
    public void showMoveDone(Cell cell, TypesOf.Color playerColor) {

    }

}



