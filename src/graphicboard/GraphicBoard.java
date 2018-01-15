package graphicboard;

import Logic.Board;
import Logic.Cell;
import Logic.Path;
import Logic.TypesOf;
import com.sun.javafx.geom.Curve;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphicBoard extends GridPane {
    private Color firstColor;
    private Color secColor;
    private Color cellColor = Color.GRAY;
    private LinearGradient firstPlayerLG;
    private LinearGradient secPlayerLG;
    /**
     * Building the graphic board
     *
     * @param playersColor - players colors .
     */
    public GraphicBoard(Color[] playersColor) {

        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GraphicBoard.fxml"));

        this.firstColor = playersColor[0];
        this.secColor = playersColor[1];

        if (firstColor == this.cellColor || secColor == this.cellColor) {
            Color[] strokeColors = {Color.BLUE, Color.AQUA};
            this.cellColor = Color.GREEN;
            if (firstColor == this.cellColor || secColor == this.cellColor) {
                this.cellColor = Color.YELLOW;
            }
        }

        Stop[] stops = new Stop[] { new Stop(0, firstColor), new Stop(1, Color.BLACK)};
         firstPlayerLG = new LinearGradient(0, 0, 2,    1, true, CycleMethod.REFLECT, stops);
        stops = new Stop[] { new Stop(0, secColor), new Stop(1, Color.BLACK)};
        secPlayerLG = new LinearGradient(0, 0, 2,    1, true, CycleMethod.REFLECT, stops);

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


    /**
     * Draws the entire board .
     *
     * @param board_-       actuall board
     * @param possibleMoves - moves to highlight . (available moves)
     */
    public void draw(Board board_, List<Path> possibleMoves) {

        int rows = board_.getRows();
        int columns = board_.getColumns();

        this.getChildren().clear();

        // Computing square size.
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / rows;
        int cellWidth = width / columns;
        int squreSide = cellHeight >= cellWidth ? cellWidth : cellHeight;

        // Drawing the Board.
        // Drawing the Board.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Adding normal cell.
                Rectangle rec = new Rectangle(squreSide - 2, squreSide - 2, this.cellColor);
                rec.setStroke(Color.GREEN);
                rec.setStrokeWidth(1);
                this.add(rec, j, i);
                // if the cell is is not empty then according to the color , the images are drawn :

                if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.black) {
                    Ellipse elipse = new Ellipse(squreSide / 3, squreSide / 6);
                    setHalignment(elipse, HPos.CENTER);
                    elipse.setFill(firstPlayerLG);
                    this.add(elipse, j, i);

                } else if (board_.getCellValue(i + 1, j + 1) == TypesOf.Color.white) {
                    Ellipse elipse = new Ellipse(squreSide / 3, squreSide / 6);
                    setHalignment(elipse, HPos.CENTER);
                    elipse.setFill(secPlayerLG);
                    this.add(elipse, j, i);
                }
            }
        }

        // Marking possible moves.
        for (Path possibleMove : possibleMoves) {
            Cell p = possibleMove.getLanding();
            Rectangle rec = new Rectangle(squreSide - 2, squreSide - 2, this.cellColor);
            rec.setStroke(Color.ORANGE);
            rec.setStrokeWidth(1);
            this.add(rec, p.getColumn() - 1, p.getRow() - 1);
        }

    }

}
