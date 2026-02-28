package structures;

import structures.basic.Player;

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

	public Player humanPlayer;
	public Player aiPlayer;
	public GameState() {
		this.humanPlayer = new Player();
		this.aiPlayer = new Player();
	}
}
