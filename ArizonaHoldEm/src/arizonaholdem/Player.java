/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arizonaholdem;

/**
 *
 * @author vbhas
 */
import java.util.ArrayList;
/*
import model.Card;
import model.PokerHand;
*/
public class Player {
	private double holdings;
	Card card1, card2;
	PokerHand highestWinningHand;

	public Player() {
		holdings = 100.00;
	}

	public double getHoldings() {
		return this.holdings;
	}

	public double dealTwo(Card input1, Card input2) {
		card1 = input1;
		card2 = input2;
		return collectAnte();
	}

	public double collectAnte() {
		holdings -= 2.00;
		return 2.00;
	}

	public void winAnte(double winnings) {
		holdings += winnings;
	}

	public double newHand(Card c1, Card c2) {
		card1 = c1;
		card2 = c2;
		return collectAnte();
	}

	public void dealCommunity(PokerHand ph) {
		highestWinningHand = findtheWinner(ph, card1, card2);
	}

	public String toString() {
		return highestWinningHand.toString();
	}

	private PokerHand findtheWinner(PokerHand ph, Card c1, Card c2) {
		ArrayList<PokerHand> phAll = new ArrayList<PokerHand>();
		Card cA = ph.getCard(0);
		Card cB = ph.getCard(1);
		Card cC = ph.getCard(2);
		Card cD = ph.getCard(3);
		Card cE = ph.getCard(4);

		phAll.add(new PokerHand(c1, cB, cC, cD, cE));
		phAll.add(new PokerHand(c1, cA, cC, cD, cE));
		phAll.add(new PokerHand(c1, cB, cA, cD, cE));
		phAll.add(new PokerHand(c1, cB, cC, cA, cE));
		phAll.add(new PokerHand(c1, cB, cC, cD, cA));
		phAll.add(new PokerHand(c2, cB, cC, cD, cE));
		phAll.add(new PokerHand(c2, cA, cC, cD, cE));
		phAll.add(new PokerHand(c2, cB, cA, cD, cE));
		phAll.add(new PokerHand(c2, cB, cC, cA, cE));
		phAll.add(new PokerHand(c2, cB, cC, cD, cA));
		phAll.add(new PokerHand(c1, c2, cA, cD, cE));
		phAll.add(new PokerHand(c1, c2, cA, cC, cE));
		phAll.add(new PokerHand(c1, c2, cA, cC, cD));
		phAll.add(new PokerHand(c1, c2, cA, cB, cE));
		phAll.add(new PokerHand(c1, c2, cA, cD, cB));
		phAll.add(new PokerHand(c1, c2, cC, cA, cB));
		phAll.add(new PokerHand(c1, c2, cC, cD, cA));
		phAll.add(new PokerHand(c1, c2, cA, cB, cD));
		phAll.add(new PokerHand(c1, c2, cA, cB, cD));
		phAll.add(new PokerHand(c1, c2, cA, cB, cC));
		phAll.add(ph);
		PokerHand max = phAll.get(0);
		for (PokerHand test : phAll) {
			if (test.compareTo(max) > 0) {
				max = test;
			}
		}
		return max;
	}

	public String twoCardtoString() {
		return card1.toString() + card2.toString();
	}

	public int compareTo(Player other) {
		return this.highestWinningHand.compareTo(other.highestWinningHand);
	}

}
