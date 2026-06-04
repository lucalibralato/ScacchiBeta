package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    public King(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        List<List<Integer>> record_one = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) record_one.add(new ArrayList<>(Arrays.asList(this.x + j, this.y + i)));
            }
        }
        return selezioneValidi(record_one);
    }
}
