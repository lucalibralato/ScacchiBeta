package backend.Model;

public class Debug {
        
    public static void main(String[] args) { //debug 01 - check the avaiable moves
        
    
        Piece pwan = new Pawn(true, 1, 1);
        Piece bishop = new Bishop(true, 0, 2);
        Piece horse = new Horse(true, 0, 3);
        Piece king = new King(true, 0, 4);
        Piece queen = new  Queen(true, 0, 5);
        Piece tower = new Tower(true, 0, 6);

        pwan.printAvaiableMove();
        bishop.printAvaiableMove();
        horse.printAvaiableMove();
        king.printAvaiableMove();
        queen.printAvaiableMove();
        tower.printAvaiableMove();
        
    }//*/

}
