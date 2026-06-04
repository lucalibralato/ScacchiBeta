package backend.Model;

import backend.Exception.EmptySquareException;
import java.util.List;

public class Empty extends Piece{

    public Empty(boolean color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<List<Integer>> possibleMove() {
        throw new EmptySquareException(null);
    }
    
}
