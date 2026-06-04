package backend.movement;

import backend.classs.Piece;

public interface FowardBackward {
    default int[] move(int x, int y, Piece piece) { //passare x positiva per andare a vanti o x negativa per andare indietro
        return new int[]{piece.getXY()[0]+x, y};
    }
}
