package graphicboard;

import Logic.Board;
import Logic.Cell;
import Logic.Path;
import Logic.TypesOf;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphicBoard extends GridPane {
    private Board board;
    private Color firstColor;
    private Color secColor;
    private int rows;
    private int columns;
    private Color[] strokeColors = {Color.BLUE, Color.AQUA};
    private Color strokeColor=Color.YELLOW;
    private Color cellColor=Color.GRAY;
    private List<Cell> moves;

    public GraphicBoard(Color [] playersColor) {

        // this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GraphicBoard.fxml"));

        // this.rows = board.getRows();
        //this.columns = board.getColumns();

        this.firstColor = playersColor[0];
        this.secColor = playersColor[1];

        if (firstColor == this.strokeColor || secColor == this.strokeColor) {
            this.strokeColor = this.strokeColors[0];
            if (firstColor == this.strokeColor || secColor == this.strokeColor) {
                this.strokeColor = this.strokeColors[1];
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

    public void selectCell(MouseEvent event) {
        System.out.println(event.getX());
    }

    public void draw(Board board_, List<Path> possibleMoves) {

        System.out.println("hEY");
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
                Rectangle rec = new Rectangle(squreSide-2, squreSide-2, this.cellColor);
                rec.setStroke(Color.GREEN);
                rec.setStrokeWidth(1);
                this.add(rec, j, i);
                if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.black) {
                    Ellipse elipse = new Ellipse(squreSide / 3, squreSide / 6);
                    setHalignment(elipse, HPos.CENTER);
                    elipse.setStroke(strokeColor);
                    elipse.setFill(firstColor);
                    elipse.setStrokeWidth(1);
                    this.add(elipse, j, i);

                } else if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.white) {
                    Ellipse elipse = new Ellipse(squreSide / 3, squreSide / 6);
                    setHalignment(elipse, HPos.CENTER);
                    elipse.setStroke(strokeColor);
                    elipse.setFill(secColor);
                    elipse.setStrokeWidth(1);
                    this.add(elipse, j, i);
                }
            }
        }
        for (Path possibleMove : possibleMoves) {
            Cell p = possibleMove.getLanding();
            Rectangle rec = new Rectangle(squreSide-2, squreSide-2, this.cellColor);
            rec.setStroke(Color.ORANGE);
            rec.setStrokeWidth(1);
            this.add(rec, p.getColumn() -1, p.getRow() -1);
        }

    }

}
