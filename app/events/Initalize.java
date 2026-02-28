package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import demo.CommandDemo;
import demo.Loaders_2024_Check;
import structures.GameState;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

/**
 * Indicates that both the core game loop in the browser is starting, meaning
 * that it is ready to recieve commands from the back-end.
 * * {
 * messageType = “initalize”
 * }
 * * @author Dr. Richard McCreadie
 *
 */
public class Initalize implements EventProcessor {

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// hello this is a change

		gameState.gameInitalised = true;

		gameState.something = true;

		// [SC-102] Initialize the 9x5 game board when the game starts
		gameState.initBoardArray();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 5; y++) {
				Tile tile = BasicObjectBuilders.loadTile(x, y);
				gameState.board[x][y] = tile;
				BasicCommands.drawTile(out, tile, 0);
			}
		}

		// [SC-102] Comment out the auto mode demo as requested for the actual gameplay
		// CommandDemo.executeDemo(out);
	}

}
