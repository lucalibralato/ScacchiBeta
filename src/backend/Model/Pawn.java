package backend.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private boolean hasMoved = false;

    public Pawn(boolean color, int x, int y) {
        super(color, x, y);
    }

    private int direction() {
        return this.color ? -1 : 1; // bianco sale (y decresce), nero scende (y cresce)
    }

    @Override
    public List<List<Integer>> possibleMove() {
        List<List<Integer>> record_one = new ArrayList<>();
        int dir = direction();
        record_one.add(Arrays.asList(this.x, this.y + dir));
        if (!hasMoved) record_one.add(Arrays.asList(this.x, this.y + 2 * dir));
        record_one.add(Arrays.asList(this.x - 1, this.y + dir));
        record_one.add(Arrays.asList(this.x + 1, this.y + dir));
        return selezioneValidi(record_one);
    }

    @Override
    protected List<List<Integer>> availableMove(Chessboard chessboard) {
        List<List<Integer>> result = new ArrayList<>();
        int dir = direction();

        // avanzamento
        List<Integer> oneStep = Arrays.asList(this.x, this.y + dir);
        if (!checkIfIsOut(oneStep)) {
            Piece target = chessboard.getSquare(oneStep.get(0), oneStep.get(1)).getPiece();
            if (target instanceof Empty) {
                result.add(oneStep);
                if (!hasMoved) {
                    List<Integer> twoStep = Arrays.asList(this.x, this.y + 2 * dir);
                    if (!checkIfIsOut(twoStep)) {
                        Piece target2 = chessboard.getSquare(twoStep.get(0), twoStep.get(1)).getPiece();
                        if (target2 instanceof Empty) result.add(twoStep);
                    }
                }
            }
        }

        // catture diagonali
        for (int dx : new int[]{-1, 1}) {
            List<Integer> diag = Arrays.asList(this.x + dx, this.y + dir);
            if (!checkIfIsOut(diag)) {
                Piece target = chessboard.getSquare(diag.get(0), diag.get(1)).getPiece();
                if (!(target instanceof Empty) && target.color != this.color) result.add(diag);
            }
        }

        return result;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        this.hasMoved = true;
    }
}
