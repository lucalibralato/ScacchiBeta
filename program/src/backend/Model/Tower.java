package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tower extends Piece {

    public Tower(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        List<List<Integer>> record_one = new ArrayList<>();
        for (int i = 0; i < Chessboard.HEIGHT; i++) {
            for (int j = 0; j < Chessboard.WIDTH; j++) {
                if ((i == this.y && j != this.x) || (j == this.x && i != this.y))
                    record_one.add(new ArrayList<>(Arrays.asList(j, i)));
            }
        }
        return selezioneValidi(record_one);
    }
}
