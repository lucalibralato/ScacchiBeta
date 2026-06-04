package backend.Model;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    public static final int WIDTH = 8;  // x = colonna, si parte da 0
    public static final int HEIGHT = 8; // y = riga
    public static final int DIAGONAL = 8;
    private Square[][] chessboard = new Square[HEIGHT][WIDTH];

    public Chessboard() {
        for (int i = 0; i < chessboard.length; i++) { // i = y (riga)
            for (int j = 0; j < chessboard[i].length; j++) { // j = x (colonna)
                chessboard[i][j] = new Square((i + j) % 2 != 0, j, i); // somma dispari = bianco
                switch (i) {
                    case 1 -> {
                        chessboard[i][j].setPiece(new Pawn(false, j, i));
                    }
                    case 6 -> {
                        chessboard[i][j].setPiece(new Pawn(true, j, i));
                    }
                    case 0 -> {
                        boolean color = false; // nero
                        switch (j) {
                            case 0, 7 -> chessboard[i][j].setPiece(new Tower(color, j, i));
                            case 1, 6 -> chessboard[i][j].setPiece(new Horse(color, j, i));
                            case 2, 5 -> chessboard[i][j].setPiece(new Bishop(color, j, i));
                            case 3    -> chessboard[i][j].setPiece(new Queen(color, j, i));
                            case 4    -> chessboard[i][j].setPiece(new King(color, j, i));
                        }
                    }
                    case 7 -> {
                        boolean color = true; // bianco
                        switch (j) {
                            case 0, 7 -> chessboard[i][j].setPiece(new Tower(color, j, i));
                            case 1, 6 -> chessboard[i][j].setPiece(new Horse(color, j, i));
                            case 2, 5 -> chessboard[i][j].setPiece(new Bishop(color, j, i));
                            case 3    -> chessboard[i][j].setPiece(new Queen(color, j, i));
                            case 4    -> chessboard[i][j].setPiece(new King(color, j, i));
                        }
                    }
                    default -> {
                        chessboard[i][j].setPiece(new Empty(chessboard[i][j].getColor(), j, i));
                    }
                }
            }
        }
    }

    public Square getSquare(int x, int y) {
        return chessboard[y][x]; // array indicizzato [riga][colonna] = [y][x]
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        Square from = getSquare(fromX, fromY);
        Square to = getSquare(toX, toY);
        Piece piece = from.getPiece();
        piece.move(toX, toY);
        to.setPiece(piece);
        from.setPiece(new Empty(from.getColor(), fromX, fromY));
    }

    public List<List<Integer>> getAvailableMoves(int x, int y) {
        Piece piece = getSquare(x, y).getPiece();
        if (piece instanceof Empty) return new ArrayList<>();
        return piece.availableMove(this);
    }

    protected Square[][] getChessboard() {
        return this.chessboard;
    }
}
