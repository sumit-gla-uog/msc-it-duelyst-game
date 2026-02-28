package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
// import demo.CommandDemo;
// import demo.Loaders_2024_Check;
import structures.GameState;
import commands.BasicCommands;
import structures.basic.Card;
import utils.OrderedCardLoader;

/**
 * Indicates that both the core game loop in the browser is starting, meaning
 * that it is ready to recieve commands from the back-end.
 * 
 * { 
 *   messageType = “initalize”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Initalize implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// hello this is a change
		
		gameState.gameInitalised = true;

		gameState.humanPlayer.deck = OrderedCardLoader.getPlayer1Cards(2);
		gameState.aiPlayer.deck = OrderedCardLoader.getPlayer2Cards(2);
		
		for (int i = 0; i < 3; i++) {
			if (!gameState.humanPlayer.deck.isEmpty()) {
				Card drawnCard = gameState.humanPlayer.deck.remove(0);
				
				gameState.humanPlayer.hand.add(drawnCard);
				
				BasicCommands.drawCard(out, drawnCard, i + 1, 0);
			}
		}

		BasicCommands.setPlayer1Health(out, gameState.humanPlayer);
		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
		
		// User 1 makes a change
		// CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//Loaders_2024_Check.test(out);
	}

}


