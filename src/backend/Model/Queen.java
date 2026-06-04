package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

    public Queen(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        List<List<Integer>> record_one = new ArrayList<>();
        for (int i = 0; i < Chessboard.HEIGHT; i++) {
            if (this.getXY().get(1) != i) record_one.add(Arrays.asList(this.getXY().get(0), i));
        }
        for (int i = 0; i < Chessboard.WIDTH; i++) {
            if (this.getXY().get(0) != i) record_one.add(Arrays.asList(i, this.getXY().get(1)));
        }
        
        for (int i = 1; i < Chessboard.DIAGONAL; i++) {
            record_one.add(Arrays.asList(this.getXY().get(0) + i, this.getXY().get(1) + i));
            record_one.add(Arrays.asList(this.getXY().get(0) + i, this.getXY().get(1) - i));
            record_one.add(Arrays.asList(this.getXY().get(0) - i, this.getXY().get(1) + i));
            record_one.add(Arrays.asList(this.getXY().get(0) - i, this.getXY().get(1) - i));
        }

        record_one.removeIf(p -> p.get(0).equals(this.getXY().get(0)) && p.get(1).equals(this.getXY().get(1)));

        return selezioneValidi(record_one);
    }
    
}
