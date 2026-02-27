package structures;
import structures.basic.Tile;
/**
 * This class can be used to hold information about the on-going game.
 * Its created with the GameActor.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class GameState {

	
	public boolean gameInitalised = false;
	
	public boolean something = false;

	// 9x5 grid for the game board (SC-102)
	public Tile[][] board = new Tile[9][5];
	// initialize the board with empty Tile objects
	public void initBoard() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 5; y++) {
				board[x][y] = new Tile();
			}
		}
	}
}
