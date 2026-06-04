package frontend;

import backend.Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ChessboardPanel extends JPanel implements MouseListener {
    private static final int CELL_SIZE = 80;
    private static final int BOARD_SIZE = 8;
    private static final int STATUS_HEIGHT = 40;

    private static final Color LIGHT = new Color(240, 217, 181);
    private static final Color DARK = new Color(181, 136, 99);
    private static final Color SELECTED = new Color(50, 180, 50, 190);
    private static final Color MOVE_HINT = new Color(100, 200, 100, 150);
    private static final Color CAPTURE_HINT = new Color(220, 70, 70, 160);

    private final Game game;
    private List<List<Integer>> highlightedMoves = new ArrayList<>();
    private int selectedX = -1, selectedY = -1;

    public ChessboardPanel() {
        this.game = new Game();
        setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE + STATUS_HEIGHT));
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawHighlights(g);
        drawPieces(g);
        drawStatus(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                g.setColor((row + col) % 2 == 0 ? LIGHT : DARK);
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawHighlights(Graphics g) {
        if (selectedX >= 0) {
            g.setColor(SELECTED);
            g.fillRect(selectedX * CELL_SIZE, selectedY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        for (List<Integer> move : highlightedMoves) {
            int mx = move.get(0);
            int my = move.get(1);
            Piece target = game.getChessboard().getSquare(mx, my).getPiece();
            g.setColor(target instanceof Empty ? MOVE_HINT : CAPTURE_HINT);
            g.fillRect(mx * CELL_SIZE, my * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawPieces(Graphics g) {
        Font font = new Font("Segoe UI Symbol", Font.PLAIN, 52);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                Piece piece = game.getChessboard().getSquare(x, y).getPiece();
                if (!(piece instanceof Empty)) {
                    String symbol = getPieceSymbol(piece);
                    int drawX = x * CELL_SIZE + (CELL_SIZE - fm.stringWidth(symbol)) / 2;
                    int drawY = y * CELL_SIZE + (CELL_SIZE + fm.getAscent() - fm.getDescent()) / 2;
                    g.setColor(Color.BLACK);
                    g.drawString(symbol, drawX, drawY);
                }
            }
        }
    }

    private void drawStatus(Graphics g) {
        int boardBottom = CELL_SIZE * BOARD_SIZE;
        g.setColor(new Color(40, 40, 40));
        g.fillRect(0, boardBottom, CELL_SIZE * BOARD_SIZE, STATUS_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        String turn = game.getCurrentTurn() ? "Turno: Bianco" : "Turno: Nero";
        g.drawString(turn, 10, boardBottom + 26);
    }

    private String getPieceSymbol(Piece piece) {
        boolean white = piece.getColor();
        if (piece instanceof King)   return white ? "♔" : "♚";
        if (piece instanceof Queen)  return white ? "♕" : "♛";
        if (piece instanceof Tower)  return white ? "♖" : "♜";
        if (piece instanceof Bishop) return white ? "♗" : "♝";
        if (piece instanceof Horse)  return white ? "♘" : "♞";
        if (piece instanceof Pawn)   return white ? "♙" : "♟";
        return "";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / CELL_SIZE;
        int row = e.getY() / CELL_SIZE;
        if (col < 0 || col >= BOARD_SIZE || row < 0 || row >= BOARD_SIZE) return;

        if (selectedX >= 0) {
            boolean moved = game.tryMove(selectedX, selectedY, col, row);
            if (moved) {
                clearSelection();
            } else {
                Piece piece = game.getChessboard().getSquare(col, row).getPiece();
                if (!(piece instanceof Empty) && piece.getColor() == game.getCurrentTurn()) {
                    selectedX = col;
                    selectedY = row;
                    highlightedMoves = game.getChessboard().getAvailableMoves(col, row);
                } else {
                    clearSelection();
                }
            }
        } else {
            Piece piece = game.getChessboard().getSquare(col, row).getPiece();
            if (!(piece instanceof Empty) && piece.getColor() == game.getCurrentTurn()) {
                selectedX = col;
                selectedY = row;
                highlightedMoves = game.getChessboard().getAvailableMoves(col, row);
            }
        }
        repaint();
    }

    private void clearSelection() {
        selectedX = -1;
        selectedY = -1;
        highlightedMoves = new ArrayList<>();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
