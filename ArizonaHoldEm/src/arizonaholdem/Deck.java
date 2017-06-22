/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arizonaholdem;

/**
 *
 * @author vbhas
 */import java.util.ArrayList;
import java.util.Collections;
//import java.util.List;


public class Deck {
	ArrayList<Card> cards;

	public Deck() {
		this.newGame();
	}

	public Card deal() {
		return cards.remove((int) (Math.random() * cards.size()));
	}

	public void newGame() {
		cards = new ArrayList<Card>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(cards);
	}
}
