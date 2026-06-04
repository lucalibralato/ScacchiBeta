package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Horse extends Piece{

    public Horse(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        
        List<List<Integer>> record_one = new ArrayList<>(Arrays.asList(Arrays.asList(this.x-1, this.y+2),Arrays.asList(this.x+1, this.y+2),Arrays.asList(this.x-1, this.y-2),Arrays.asList(this.x+1, this.y-2),Arrays.asList(this.x-2, this.y+1),Arrays.asList(this.x+2, this.y+1),Arrays.asList(this.x-2, this.y-1),Arrays.asList(this.x+2, this.y-1)));

        return selezioneValidi(record_one);
    }
    
    /*
    il cavallo si può muovere in:
    x-1, y+2
    x+1, y+2
    x-1, y-2
    x+1, y-2
    x-2, y+1
    x+2, y+1
    x-2, y-1
    x+2, y-1
    */
}
