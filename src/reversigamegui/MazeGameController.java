package reversigamegui;

import ConfigWin.ConfigWinController;
import Logic.*;
import Logic.Cell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import graphicboard.GraphicBoard;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;


import javax.management.timer.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class MazeGameController implements Initializable, Display {
    @FXML
    private GridPane boardContainer_;
    //private Board board;
    private GraphicBoard graphicBoard;
    private ConfigWinController configWinController;
    private Color [] playersColor;
    private Integer boardSize;
    private Game game;
    private String [] playersName;
    @FXML
    private Pane currPlayerImg;
    @FXML
    private Pane firstPlayerImg;
    @FXML
    private Pane secPlayerImg;
    private Pane [] playersImg;
    public javafx.scene.control.Label firstPlayerScore;
    public javafx.scene.control.Label secPlayerScore;

    public javafx.scene.control.Label errorLabel;

    private javax.management.timer.Timer timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readSettings();
        playersName=new String[2];
        playersImg=new Pane[2];
        playersImg[0]=firstPlayerImg;
        playersImg[1]=secPlayerImg;
        Board board = new Board(boardSize, boardSize, new ArrayList<>(), new ArrayList<>());
        game = new Game(boardSize, boardSize, this, this, board);

        graphicBoard = new GraphicBoard(playersColor);

        graphicBoard.setPrefWidth(400);
        graphicBoard.setPrefHeight(400);
        boardContainer_.getChildren().add(0, graphicBoard);
        //boardContainer_.add(graphicBoard,0,0);
        boardContainer_.setOnKeyPressed(graphicBoard.getOnKeyPressed());
        boardContainer_.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override public void handle(javafx.scene.input.MouseEvent event) {
                int  height=(int)boardContainer_.getPrefHeight();
                int width = (int)boardContainer_.getPrefWidth();
                int cellHeight = height / board.getRows();
                int cellWidth = width / board.getColumns();
                int squreSide = cellHeight >= cellWidth ? cellWidth : cellHeight;
                squreSide-=1;
                Integer x=(int)Math.floor(event.getSceneX()/squreSide)+1;
                Integer y=(int)Math.floor(event.getSceneY()/squreSide)+1;
                Cell c=new Cell(y,x);
                game.makeMove(c);
                System.out.println("(x: "       +   x    + ", y: "       +y);
                System.out.println("real2 (x: "       +   event.getSceneX()    + ", y: "       +event.getSceneY());
                System.out.println("real (x: "       +   event.getX()    + ", y: "       +event.getY());

                System.out.println("(w: "       +   cellWidth);


            }
        });
      //  boardContainer_.setOnMouseClicked(graphicBoard.getOnMouseClicked());
        boardContainer_.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue()-120;
            graphicBoard.setPrefWidth(boardNewWidth);
            boardContainer_.setPrefWidth(boardNewWidth);
            graphicBoard.draw(board,game.getValidMoves());
            notification();
        });
        boardContainer_.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue()-120;
            graphicBoard.setPrefHeight(boardNewWidth);
            boardContainer_.setPrefHeight(boardNewWidth);
            graphicBoard.draw(board,game.getValidMoves());
            notification();
        });


    }
    public void notification(){
        int currPlayer=game.getCurrPlayer();

        Ellipse currPlayerDisk = new Ellipse(40,40,20, 10);
        currPlayerDisk.setStroke(Color.BLUE);
        currPlayerDisk.setFill(playersColor[currPlayer]);
        currPlayerDisk.setStrokeWidth(1);

        currPlayerImg.getChildren().clear();
        currPlayerImg.getChildren().add(0,currPlayerDisk);

        Ellipse firstPlayerDisk = new Ellipse(40,40,20, 10);
        firstPlayerDisk.setStroke(Color.BLUE);
        firstPlayerDisk.setFill(playersColor[0]);
        firstPlayerDisk.setStrokeWidth(1);

        playersImg[currPlayer].getChildren().clear();
        playersImg[currPlayer].getChildren().add(0,firstPlayerDisk);

        Ellipse secPlayerDisk = new Ellipse(40,40,20, 10);
        secPlayerDisk.setStroke(Color.BLUE);
        secPlayerDisk.setFill(playersColor[1]);
        secPlayerDisk.setStrokeWidth(1);

        playersImg[1].getChildren().clear();
        playersImg[1].getChildren().add(0,secPlayerDisk);


        int [] scores=game.getScores();
        firstPlayerScore.setText(String.valueOf(scores[0]));
        secPlayerScore.setText(String.valueOf(scores[1]));

    }
    public void startGame(String firstPlayerName,String secPlayerName){
        playersName[0]=firstPlayerName;
        playersName[1]=secPlayerName;
    }

    private void readSettings() {
        try {
            playersColor=new Color[2];
            List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
            playersColor[0] = Color.valueOf(settings.get(0));
            playersColor[1] = Color.valueOf(settings.get(1));
            boardSize = Integer.valueOf(settings.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void show(Board board, List<Logic.Path> moves, TypesOf.Color currPlayerColor, boolean passTurn, int blacks, int whites) {
        graphicBoard.draw(board,game.getValidMoves());
        notification();
    }

    @Override
    public void showError(TypesOf.Error errorType) {
        errorLabel.setText("Error");
    }

    @Override
    public void showEndGameStatus(TypesOf.GameStatus gameStatus) {
        if(gameStatus==TypesOf.GameStatus.passTurn){
            errorLabel.setText("turn passed...");
        }else if (gameStatus==TypesOf.GameStatus.blackWon) {
            errorLabel.setText("first player won");
        }else if (gameStatus==TypesOf.GameStatus.whiteWon) {
            errorLabel.setText("second player won");
        }else if (gameStatus==TypesOf.GameStatus.tie) {
            errorLabel.setText("TIE");
        }else if (gameStatus==TypesOf.GameStatus.noOneWon){
            errorLabel.setText("no one won");
        }
    }

    @Override
    public void showMoveDone(Cell cell, TypesOf.Color playerColor) {
        //errorLabel.setText("keep going");
    }
    @FXML
    public void Menu(){
        try {
            ((Stage) boardContainer_.getScene().getWindow()).setScene
                    (new Scene(FXMLLoader.load(this.getClass().getResource("/menuWin/menuWin.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



