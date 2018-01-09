package reversigamegui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Player {
    private GridPane grid;
    private int row;
    private int col;
    private ImageView iv;

    public Player(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        // Load the player's image
        iv = new ImageView(getClass().getResource("minion.png").toExternalForm());
    }

    public void draw(int cellWidth, int cellHeight) {
        iv.setFitWidth(cellWidth);
        iv.setFitHeight(cellHeight);
        grid.getChildren().remove(iv);
        grid.add(iv, col, row);
    }

    public void moveUp() {
        row--; // need to check that player doesn't hit a wall
        redraw();
    }

    public void moveDown() {
        row++;
        redraw();
    }

    public void moveLeft() {
        col--;
        redraw();
    }

    public void moveRight() {
        col++;
        redraw();
    }

    private void redraw() {
        grid.getChildren().remove(iv);
        grid.add(iv, col, row);
    }
}
