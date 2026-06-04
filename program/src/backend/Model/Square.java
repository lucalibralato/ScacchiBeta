package backend.Model;

public class Square {
    private final boolean color; // false = nero, true = bianco
    private final int x, y;
    private Piece piece;

    public Square(boolean color, int x, int y) { // colore, colonna, riga
        this.color = color;
        this.x = x;
        this.y = y;
    }

    protected void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean getColor() {
        return this.color;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
