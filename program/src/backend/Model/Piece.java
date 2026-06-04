package backend.Model;

import backend.Enum.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Piece {
    protected boolean color; // true = white, false = black
    protected int x, y;

    public Piece(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public boolean getColor() {
        return this.color;
    }

    public List<Integer> getXY() {
        return new ArrayList<>(Arrays.asList(this.x, this.y));
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract List<List<Integer>> possibleMove(); // mosse geometricamente possibili, senza considerare ostacoli

    protected boolean checkIfIsOut(List<Integer> mat) {
        if (mat.get(0) < 0 || mat.get(0) >= Chessboard.WIDTH || mat.get(1) < 0 || mat.get(1) >= Chessboard.HEIGHT)
            return true;
        return false;
    }

    protected List<List<Integer>> selezioneValidi(List<List<Integer>> record_one) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < record_one.size(); i++) {
            if (!checkIfIsOut(record_one.get(i)))
                result.add(record_one.get(i));
        }
        return result;
    }

    public void printAvaiableMove() { // debugging method
        System.out.println(this.getClass().getName());
        System.out.println(this.splitByDirection(this.possibleMove()));
    }

    protected List<List<Integer>> availableMove(Chessboard chessboard) {
        HashMap<Direction, List<List<Integer>>> byDir = this.splitByDirection(this.possibleMove());
        List<List<Integer>> result = new ArrayList<>();

        for (List<List<Integer>> dirMoves : byDir.values()) {
            for (List<Integer> move : dirMoves) {
                Piece target = chessboard.getSquare(move.get(0), move.get(1)).getPiece();
                if (target instanceof Empty) {
                    result.add(move);
                } else if (target.color != this.color) {
                    result.add(move); // può catturare, ma si ferma
                    break;
                } else {
                    break; // bloccato da pezzo dello stesso colore
                }
            }
        }
        return result;
    }

    private HashMap<Direction, List<List<Integer>>> splitByDirection(List<List<Integer>> record_one) {
        HashMap<Direction, List<List<Integer>>> result = new HashMap<>();
        result.put(Direction.FORWARD, new ArrayList<>());
        result.put(Direction.BACKWARD, new ArrayList<>());
        result.put(Direction.LEFT, new ArrayList<>());
        result.put(Direction.RIGHT, new ArrayList<>());
        result.put(Direction.DIAGONAL_RIGHT_UP, new ArrayList<>());
        result.put(Direction.DIAGONAL_RIGHT_DOWN, new ArrayList<>());
        result.put(Direction.DIAGONAL_LEFT_UP, new ArrayList<>());
        result.put(Direction.DIAGONAL_LEFT_DOWN, new ArrayList<>());

        for (List<Integer> list : record_one) {
            Direction dir = this.getDirection(this.getXY(), list);
            if (dir != null) result.get(dir).add(list);
        }

        result.forEach((key, value) -> quickSort(value, 0, value.size() - 1));
        return result;
    }

    private Direction getDirection(List<Integer> p1, List<Integer> p2) {
        if (p1.get(0) < p2.get(0) && p1.get(1).equals(p2.get(1)))
            return Direction.RIGHT;
        else if (p1.get(0) > p2.get(0) && p1.get(1).equals(p2.get(1)))
            return Direction.LEFT;
        else if (p1.get(0).equals(p2.get(0)) && p1.get(1) < p2.get(1))
            return Direction.FORWARD;
        else if (p1.get(0).equals(p2.get(0)) && p1.get(1) > p2.get(1))
            return Direction.BACKWARD;
        else if (p1.get(0) < p2.get(0) && p1.get(1) < p2.get(1))
            return Direction.DIAGONAL_RIGHT_UP;
        else if (p1.get(0) < p2.get(0) && p1.get(1) > p2.get(1))
            return Direction.DIAGONAL_RIGHT_DOWN;
        else if (p1.get(0) > p2.get(0) && p1.get(1) < p2.get(1))
            return Direction.DIAGONAL_LEFT_UP;
        else if (p1.get(0) > p2.get(0) && p1.get(1) > p2.get(1))
            return Direction.DIAGONAL_LEFT_DOWN;
        return null;
    }

    private int distanceFrom(List<Integer> move) {
        return Math.abs(move.get(0) - this.x) + Math.abs(move.get(1) - this.y);
    }

    private void quickSort(List<List<Integer>> arr, int low, int high) {
        if (low < high) {
            int pivotDist = distanceFrom(arr.get(high));
            int i = low;
            for (int j = low; j < high; j++) {
                if (distanceFrom(arr.get(j)) <= pivotDist) {
                    List<Integer> temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                    i++;
                }
            }
            List<Integer> temp = arr.get(i);
            arr.set(i, arr.get(high));
            arr.set(high, temp);
            int pivotIndex = i;
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
}
