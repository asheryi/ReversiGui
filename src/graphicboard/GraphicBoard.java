package graphicboard;

import Logic.Board;
import Logic.Cell;
import Logic.TypesOf;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphicBoard extends GridPane {
    private Board board;
    private Color firstColor;
    private Color secColor;
    private int rows;
    private int columns;
    private Color[] cellColors = {Color.GRAY, Color.YELLOW, Color.AQUA};
    private Color cellColor;
    private List<Cell> moves;

    public GraphicBoard() {
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GraphicBoard.fxml"));

        this.rows = 8;
        this.columns = 8;
        List<Cell> blacks = new ArrayList<>(2);
        List<Cell> whites = new ArrayList<>(2);

        blacks.add(new Cell(rows / 2, columns / 2 + 1));
        blacks.add(new Cell(rows / 2 + 1, columns / 2));

        whites.add(new Cell(rows / 2, columns / 2));
        whites.add(new Cell(rows / 2 + 1, columns / 2 + 1));

        this.board = new Board(8, 8, blacks, whites);

        this.firstColor = Color.BLACK;
        this.secColor = Color.WHITE;

        this.cellColor = Color.GRAY;
        if (firstColor == this.cellColor || secColor == this.cellColor) {
            this.cellColor = this.cellColors[0];
            if (firstColor == this.cellColor || secColor == this.cellColor) {
                this.cellColor = this.cellColors[1];
            }
        }

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public GraphicBoard(Color firstPlayerColor, Color secPlayerColor) {
       // this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GraphicBoard.fxml"));

       // this.rows = board.getRows();
        //this.columns = board.getColumns();

        this.firstColor = firstPlayerColor;
        this.secColor = secPlayerColor;

        this.cellColor = Color.GRAY;
        if (firstColor == this.cellColor || secColor == this.cellColor) {
            this.cellColor = this.cellColors[0];
            if (firstColor == this.cellColor || secColor == this.cellColor) {
                this.cellColor = this.cellColors[1];
            }
        }

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void draw(Board board_) {
        this.rows = board_.getRows();
        this.columns = board_.getColumns();
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / rows;
        int cellWidth = width / columns;
        int squreSide = cellHeight >= cellWidth ? cellWidth : cellHeight;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(i == 7 && j ==7){
                    System.out.println();
                }
                Rectangle rec = new Rectangle(squreSide, squreSide, this.cellColor);
                rec.setStroke(Color.GREEN);
                this.add(rec, j, i);
                if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.black) {
                    //Rectangle rec = new Rectangle(squreSide, squreSide, this.firstColor);
                    Ellipse elipse = new Ellipse(squreSide / 3, squreSide / 6);
                    // Ellipse elipse2 = new Ellipse(0, 0, squreSide / 2, squreSide / 8);

                    elipse.setStroke(Color.BLUE);
                    elipse.setStrokeWidth(3);
                    //rec.setStrokeWidth(1.5);
                    //rec.setStroke(Color.GREEN);
                    // this.add(rec, j, i);
                    if (i == 3 && j == 4 || i == 4 && j == 3) {
                        this.setHalignment(elipse, HPos.CENTER);
                        this.add(elipse, 4, 3);
                        Ellipse elipse2 = new Ellipse(squreSide / 2, squreSide / 2, squreSide / 3, squreSide / 6);
                        this.add(elipse2, 3, 4);
                    }

                } else if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.white) {
                    // Rectangle rec = new Rectangle(squreSide, squreSide, this.secColor);
                    //rec.setStroke(Color.GREEN);
                    //rec.setStrokeWidth(1.5);
                    //this.add(rec, j, i);
                }
            }
        }

    }
}
