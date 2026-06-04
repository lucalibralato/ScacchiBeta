package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        List<List<Integer>> record_one = new ArrayList<>();
        for (int i = 0; i < Chessboard.HEIGHT; i++) {
            for (int j = 0; j < Chessboard.WIDTH; j++) {
                if (Math.abs(this.y-i) == Math.abs(this.x-j) && Math.abs(this.y-i) != 0 && Math.abs(this.x-j) != 0) record_one.add(new ArrayList<>(Arrays.asList(j, i)));
            }
        }
        return selezioneValidi(record_one);
    }
    /*
    le coordinate sono in diagonale se il valore assoluto della differenza tra le x (x mia posizione - x dove volgio andare) è uguale al valore assoluto della differenza delle y (y mia posizione - y dove volgio andare)
    */    
}
