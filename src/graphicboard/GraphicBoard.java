package graphicboard;

import Logic.Board;
import Logic.TypesOf;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class GraphicBoard extends GridPane {
    private Board board;
    private Color firstColor;
    private Color secColor;
    private int rows;
    private int columns;

    public GraphicBoard(Board board, Color firstPlayerColor, Color secPlayerColor) {
        this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GraphicBoard.fxml"));

        this.rows = board.getRows();
        this.columns = board.getColumns();

        this.firstColor = firstPlayerColor;
        this.secColor = secPlayerColor;

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void draw() {

        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / rows;
        int cellWidth = width / columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board.getCellValue(i + 1, j + 1) == TypesOf.Color.black)
                    this.add(new Rectangle(cellWidth, cellHeight,
                            this.firstColor), j, i);
                else
                    this.add(new Rectangle(cellWidth, cellHeight,
                            this.secColor), j, i);
            }
        }

    }
}
