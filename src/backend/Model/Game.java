package backend.Model;

import java.util.List;

public class Game {
    private Chessboard chessboard;
    private boolean currentTurn = true; // true = bianco, false = nero

    public Game() {
        this.chessboard = new Chessboard();
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public boolean getCurrentTurn() {
        return currentTurn;
    }

    public boolean tryMove(int fromX, int fromY, int toX, int toY) {
        Piece piece = chessboard.getSquare(fromX, fromY).getPiece();
        if (piece instanceof Empty) return false;
        if (piece.getColor() != currentTurn) return false;

        List<List<Integer>> moves = chessboard.getAvailableMoves(fromX, fromY);
        for (List<Integer> move : moves) {
            if (move.get(0).equals(toX) && move.get(1).equals(toY)) {
                chessboard.movePiece(fromX, fromY, toX, toY);
                currentTurn = !currentTurn;
                return true;
            }
        }
        return false;
    }
}
