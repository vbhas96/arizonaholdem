/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arizonaholdem;

/**
 *
 * @author vbhas
 */import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;


public class Dealer {
	Scanner sc;
	Player[] players;
	// Deck dealDeck;

	public Dealer(int x) {
		Scanner sc = new Scanner(System.in);
		players = new Player[x]; // all values are null still
		for (int i = 0; i < x; i++) {
			players[i] = new Player(); // all players now have holdings of 100$
		}
		while (true) {
			play();
			System.out.println("Play another game? <y or n> ");
			String playagain = sc.nextLine();
			if (playagain.equals("n")) {
				break;
			}
		}
		sc.close();
	}

	public void play() {
		Deck thisTurnDeck = new Deck();
		double pot = 0.0;
		for (int i = 0; i < players.length; i++) {
			pot += players[i].dealTwo(thisTurnDeck.deal(), thisTurnDeck.deal());// players[i].collectAnte();
		}
		PokerHand community = new PokerHand(thisTurnDeck.deal(), thisTurnDeck.deal(), thisTurnDeck.deal(),
				thisTurnDeck.deal(), thisTurnDeck.deal());
		System.out.println("Community Cards: " + community.toString());

		NumberFormat formatter = DecimalFormat.getCurrencyInstance();
		for (int y = 0; y < players.length; y++) {
			players[y].dealCommunity(community);
			System.out.println("Player " + (y+1) + ": " + formatter.format(players[y].getHoldings()) + " "
					+ players[y].twoCardtoString());
			System.out.println("   Best Hand: " + players[y].toString());
		}
		Player max = players[0];
		boolean[] indexOfTies = new boolean[players.length];
		int tieCounter = 0;
		int indexOfMax = 0;
		for (int z = 1; z < players.length; z++) {
			if (players[z].compareTo(max) > 0) {
				max = players[z];
				indexOfMax = z;
				indexOfTies = new boolean[players.length];
				tieCounter = 0;
                                for(int x = 0; x < indexOfTies.length; x++){
                                    indexOfTies[x] = false;
                                }
			}
			if (players[z].compareTo(max) == 0) {
				indexOfTies[z] = true;
				tieCounter++;
			}
		}

		if (tieCounter > 0) {
			pot /= tieCounter;
			System.out.println("Winners: ");

			for (int k = 0; k < players.length; k++) {
				if (indexOfTies[k]) {
					players[k].winAnte(pot);
					System.out.println("Player " + (k+1) + ": " + players[k].toString());
				}
			}
		} else {
			System.out.println(
					"Winner: Player " + (indexOfMax+1) + " " + formatter.format(players[indexOfMax].getHoldings()));
		}
	}
}
    