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

	// Check if the given coordinates are inside the board limits
	public boolean isWithinBoard(int x, int y) {
		return x >= 0 && x < 9 && y >= 0 && y < 5;
	}

	// Check if the tile at (x,y) is empty
	public boolean isTileFree(int x, int y) {
		// If it's out of bounds, it's definitely not a free tile
		if (!isWithinBoard(x, y)) {
			return false;
		}
		// Return true if there is no unit on this tile
		return board[x][y].getUnit() == null;
	}
	// Get the tile at specific coordinates (SC-102)
	public Tile getTile(int x, int y) {
		// Prevent game crash if someone asks for a tile outside the board
		if (!isWithinBoard(x, y)) {
			return null;
		}
		return board[x][y];
	}
}