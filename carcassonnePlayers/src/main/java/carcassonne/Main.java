package carcassonne;

import carcassonne.core.*;
import carcassonne.ui.Window;
import java.util.Scanner;

public class Main {

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}

	public static int beginGame() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Carcassonne");
		System.out.println("How many players want to play ? (2-5 players)");
		String numberOfPlayers = scan.next();
		while (isInt(numberOfPlayers) == false || Integer.parseInt(numberOfPlayers) > 5 || Integer.parseInt(numberOfPlayers) < 2) {
			System.out.println("How many players want to play ? (2-5 players)");
			numberOfPlayers = scan.next();
		}
		return Integer.parseInt(numberOfPlayers);
	}

	public static void display(Move playerMove, Player playerTurn) {
		System.out.println();
		System.out.printf("player: %d\n\ncard: %s \t dir: %s \t x: %d \t y: %d \t place: %s \n\n", playerTurn.id, playerMove.tile.name, playerMove.tile.dir, playerMove.tile.pos.x, playerMove.tile.pos.y, playerMove.place);
	}

	public static void main(String[] args) {

		/* Initialisation of the game */
	
		int numberOfPlayers = beginGame();
		Deck deck = new Deck();
		Players players = new Players(numberOfPlayers);
		setTile board = new setTile();
		Client client = new Client();
		Score score = new Score();
		Window window = new Window();
		Move playerMove = new Move(null, null, null);
		Player playerTurn = players.first();
		Tile tile;
		int round = 1;

		/* End of initialisation */
		
		/* Beginning of the game Loop */
		
		while (deck.isEmpty() == false && players.remaining() > 1) {
			System.out.printf("%n==================== ROUND : %d ====================%n", round);
			
			if (board.isPlayable(deck) == true) {
				playerMove = playerMove.askMove(playerTurn, deck);
				
				if (board.validMove(playerMove) == true) {
					board.update(playerMove, round);
					window.update(playerMove);
					display(playerMove, playerTurn);
					score.update(playerMove, board);
				} else
					playerTurn.eject(); 
				playerTurn = players.computeNext(playerTurn); 	
			} else {
				System.out.println("All the remaining cards are unplayable");
				break;
			}

			players.removeMeeples(window);
			++round;
		}
		
		score.updateEnd(board, players);
		players.displayScore();

		/* End of the game loop */
	}
}
