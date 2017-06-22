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
import java.util.Collections;



public class PokerHand {

    private ArrayList<Card> cards;
    boolean[] handCheck; // hold true false for which legal hand this.PokerHand
    // object is
    int[] handCheckRanks; // hold integer values that are from handCheck for
    // comparisons.
    int valueOfHighestHandScore; // this value will be initialized when a hand
    // is valid and will contain the highest
    // value.
    // boolean isRoyalFlush, isStraightFlush, is4ofaKind, isFullHouse, isFlush,
    // isStraight, is3ofaKind, is2Pair, is1Pair;
    // these booleans help me determine what the hand is during method checks...

    Card highCard;

    // Construct one PokerHand
    // Precondition: All five cards are unique
    public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {

        cards = new ArrayList<Card>();
        checkEqualCards(c1, c2, c3, c4, c5);

        cards.add(c1); // index 0
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5); // index 4
        Collections.sort(cards);
        highCard = cards.get(4); // IF
        handCheck = new boolean[9];
        handCheckRanks = new int[9];
        for (int x = 0; x < handCheck.length; x++) { // initialize all values to
            // false!
            handCheck[x] = false;
            handCheckRanks[x] = -1;
        }
        if (this.hasOnePair(this) != -1) {
            handCheck[0] = true;
            handCheckRanks[0] = this.hasTwoPairLow(this);

        }

        if (this.hasTwoPairLow(this) != -1) {
            handCheck[1] = true;
            handCheckRanks[1] = this.hasTwoPairLow(this);

        }
        if (this.hasThreeOfAKind(this) != -1) {
            handCheck[2] = true;
            handCheckRanks[2] = this.hasThreeOfAKind(this);

        }
        if (this.hasStraight(this) != -1) {
            handCheck[3] = true;
            handCheckRanks[3] = this.hasStraight(this);

        }
        if (this.hasFlush(this) != -1) {
            handCheck[4] = true;
            handCheckRanks[4] = this.hasFlush(this);
        }
        if (this.hasFullHouse(this) != -1) {
            handCheck[5] = true;
            handCheckRanks[5] = this.hasFullHouse(this);
        }
        if (this.hasFourOfAKind(this) != -1) {
            handCheck[6] = true;
            handCheckRanks[6] = this.hasFourOfAKind(this);
        }
        if (this.hasStraightFlush(this) != -1) {
            handCheck[7] = true;
            handCheckRanks[7] = this.hasStraightFlush(this);
        }
        if (this.hasRoyalFlush(this) != -1) {
            handCheck[8] = true;
            handCheckRanks[8] = this.hasRoyalFlush(this);
        }

    }

    public int tieBreaker(PokerHand ph) {
        for (int i = 4; i >= 0; i--) {
            int compThis = this.cards.get(i).getRank();
            int compOther = ph.cards.get(i).getRank();
            if (compThis != compOther) {
                return compThis - compOther;
            }
        }
        return 0;
    }
    public Card getCard(int index){
        return cards.get(index);
    }
    public static void checkEqualCards(Card c1, Card c2, Card c3, Card c4, Card c5){
        if (c1.equals(c2) || c1.equals(c3) || c1.equals(c4) || c1.equals(c5)) {
            // throw DuplicateCardException;
        }
        if (c2.equals(c3) || c2.equals(c4) || c2.equals(c5)) {

        }
        if (c3.equals(c4) || c3.equals(c5)) {

        }
        if (c4.equals(c5)) {

        }

    }

    // Get the rank of any of the five cards
    // Precondition: index >= 0 and index <=4
    private int getValue(int index) {
        if (index >= 0 && index <= 4) {
            return cards.get(index).getRank();
        } else {
            return -1; // as an error statement if someone is deliberately
        }						// trying to break the code!
    }

    // Return true if there are exactly two pairs in this hand
    // Precondition: cards is sorted.
    /*
	 * public boolean hasTwoPair() { int numPairs = 0; for (int i = 0; i <
	 * cards.size() - 2; i++) { if (cards.get(i) == cards.get(i + 1))
	 * numPairs++; } return numPairs == 2; }
     */
    /**
     * @param other The PokerHand to be compared to this
     *
     * @return Return 0 if this and other are tied (have two pair and the ranks
     * of the other fifth card value are equal. Return +1 if this is a better
     * hand than other. Or return -1 if other is a better hand than this.
     *
     * Precondition: this and other both have a valid two-pair hand with all
     * unique Cards
     */
    public int compareTo(PokerHand ph) {
        int indexOfThisHand = findTrue(this.handCheck);
        int indexOfOtherHand = findTrue(ph.handCheck);
        if ((indexOfThisHand - indexOfOtherHand) == 0) {
            return this.tieBreaker(ph);
        }
        return (indexOfThisHand - indexOfOtherHand);
    }

    public int findTrue(boolean[] whatHand) {
        // last index of boolean array
        for (int count = whatHand.length - 1; count >= 0; count--) {
            if (whatHand[count]) {
                return count; // Return the highest value of true.
            }
        }
        return -1; // if it is none of the valid hands, return
    }

    public int hasRoyalFlush(PokerHand ph) {

        Suit suitCheck = cards.get(0).getSuit();
        int flushChecker = 10;
        int handIndex = 0;
        while (flushChecker < 15) { // 14 is max value
            if (ph.cards.get(handIndex).getRank() == flushChecker) {
                if (ph.cards.get(handIndex).getSuit().equals(suitCheck)) {
                    flushChecker++;
                    handIndex++;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        return ph.cards.get(4).getRank(); // if you made it this far, it's
        // definitely a royal flush.

    }

    public int hasStraightFlush(PokerHand ph) {
        Suit suitCheck = cards.get(0).getSuit();
        int rankChecker = cards.get(0).getRank();
        /*
		 * special cases: Ace High vs Ace Low and how to model it: 1) if set
		 * contains 2,3,4,5,A as a special model
         */
        // check for AceLow straights first as an edge case:
        if (ph.cards.get(0).getRank() == 2 && ph.cards.get(4).getRank() == 14) { // if
            // OFF
            // THE
            // BAT
            // you
            // know
            // that
            // there's
            // a
            // 2
            // and
            // an
            // A
            int aceLowCounter = 3;
            boolean trustTheProcess = true;
            for (int index = 1; index < 4; index++) { // check the cards in the
                // middle to see if it's
                // a straight flush

                if (ph.cards.get(index).getRank() == aceLowCounter && ph.cards.get(index).getSuit().equals(suitCheck)) {
                    aceLowCounter++;
                } else {
                    trustTheProcess = false;
                }
            }

            if (trustTheProcess) { // if you can trust the process,
                Card temp = ph.cards.remove(4); // grab the Ace
                ph.cards.add(0, temp);
                return ph.cards.get(3).getRank(); // Return the highest value in
                // the Ace Low Straight,
                // which should be 5...
                // 2,3,4,5,A
            }

        }
        int handIndex = 0;
        while (handIndex < 5) {
            if (ph.cards.get(handIndex).getRank() == rankChecker) {
                if (ph.cards.get(handIndex).getSuit().equals(suitCheck)) {
                    rankChecker++;
                    handIndex++;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        return ph.cards.get(4).getRank(); // if you made it this far, it's
        // definitely a StraightFlush, not
        // AceLow, so return the highest
        // value.
    }

    private int indexOfRank(int x, PokerHand ph) {
        for (int i = 0; i < 5; i++) {
            if (ph.cards.get(i).getRank() == x) {
                return i;
            }
        }
        return -1;
    }

    public int hasFullHouse(PokerHand ph) {
        int threeRank = hasThreeOfAKind(ph);
        int pairRank = hasOnePair(ph);

        boolean isFullHouse = false;
        if (threeRank != -1 && pairRank != -1) {
            isFullHouse = true;
        }
        if (isFullHouse) {
            if (pairRank > threeRank) {
                int indexOf2Pair = indexOfRank(pairRank, ph); // BOOKMARK COME
                // BACK TO THIS
                // BITCH

                if (indexOf2Pair != 0) {
                    Card temp1 = ph.cards.remove(3);
                    Card temp2 = ph.cards.remove(4);
                    ph.cards.add(0, temp1);
                    ph.cards.add(0, temp2);
                }
            }
            return threeRank;
        }
        /*
		 * if (threeRank != -1 && pairRank != -1 && threeRank!= pairRank) {
		 * return threeRank; }
         */
        return -1;
    }

    public int hasFlush(PokerHand ph) {
        Suit coatCheck = ph.cards.get(0).getSuit();
        boolean trustTheProcess = true;
        for (int index = 0; index < 5; index++) {
            if (ph.cards.get(index).getSuit() != coatCheck) {
                trustTheProcess = false;
            }
        }
        if (trustTheProcess) {
            return ph.highCard.getRank(); // throw back the highest value in the
            // flush, as this could be useful in
            // comparisons!
        }
        return -1;
    }

    public int hasStraight(PokerHand ph) {
        int rankChecker = cards.get(0).getRank(); // this straight must
        // increment from
        // rankchecker
        /*
		 * special cases: Ace High vs Ace Low and how to model it: 1) if set
		 * contains 2,3,4,5,A as a special model
         */
        // check for AceLow straights first as an edge case:
        if (rankChecker == 2 && ph.highCard.getRank() == 14) { // if OFF

            int aceLowCounter = 3;
            boolean trustTheProcess = true;
            for (int index = 1; index <= 3; index++) { // check the cards in the
                // middle to see if it's
                // an ace low straight
                if (cards.get(index).getRank() == aceLowCounter) {
                    aceLowCounter++;
                } else {
                    trustTheProcess = false;
                }
            }

            if (trustTheProcess && ph.cards.get(3).getRank() == 5) { // if you
                // can
                // trust
                // the
                // process,
                // and
                // just
                // do a
                // quick
                // check
                // just
                // in
                // case
                return ph.cards.get(3).getRank();// return the highest card in
                // the set, which should be
                // a 5
            }

        }
        int handIndex = 0;

        while (handIndex < 5) { // 14 is max value
            if (cards.get(handIndex).getRank() == rankChecker) {
                rankChecker++;
                handIndex++;
            } else // if this test fails even once, it will return -1
            {
                return -1;
            }
        }
        /*
		 * this code can be implemented IF wrap around checks are necessary. ie:
		 * if Q K A 2 3 is a straight if(rankChecker == 15) //achieve
		 * wrap-around checks. rankChecker = 2;
         */
        return highCard.getRank(); // return the rank of the highest value in
        // the straight
    }

    public int hasFourOfAKind(PokerHand ph) {

        for (int i = 0; i < 2; i++) {
            if (ph.getValue(i) == ph.getValue(i + 1) && ph.getValue(i + 1) == ph.getValue(i + 2)
                    && ph.getValue(i + 2) == ph.getValue(i + 3)) {
                return ph.getValue(i);
            }
        }
        return -1;

    }

    public int hasThreeOfAKind(PokerHand ph) {

        for (int i = 0; i < 3; i++) {
            if (ph.getValue(i) == ph.getValue(i + 1) && ph.getValue(i + 1) == ph.getValue(i + 2)) {
                return ph.getValue(i);
            }
        }
        return -1;
    }

    public int hasTwoPairLow(PokerHand ph) {
        for (int i = 0; i < 4; i++) {
            if (ph.getValue(i) == ph.getValue(i + 1)) {
                try {
                    if (ph.getValue(i + 1) != ph.getValue(i + 2)) { // make sure
                        // there's
                        // no
                        // misinterpretation
                        // between
                        // pair and
                        // 3 of a
                        // kind/4of
                        // a kind
                        return ph.getValue(i);
                    }
                } catch (IndexOutOfBoundsException e) {
                }

            }
        }

        return -1;
    }

    public int hasTwoPairHigh(PokerHand ph) {
        for (int i = 4; i > 0; i--) {
            if (ph.getValue(i) == ph.getValue(i - 1)) {
                try {
                    if (ph.getValue(i - 1) != ph.getValue(i - 2)) { // make sure
                        // it's not
                        // a 3pair
                        // by
                        // accident!
                        return ph.getValue(i);
                    }
                } catch (IndexOutOfBoundsException e) {
                }

            }
        }
        return 0;
    }

    public int hasOnePair(PokerHand ph) {
        for (int i = 0; i < 4; i++) {
            if (ph.cards.get(i).getRank() == ph.cards.get(i + 1).getRank()) {
                return ph.cards.get(i).getRank();
            }

        }
        return -1;
    }

    public String toString() {
        String result = "";
        result += cards.get(0) + " ";
        result += cards.get(1) + " ";
        result += cards.get(2) + " ";
        result += cards.get(3) + " ";
        result += cards.get(4);
        return result;
    }

}
/*
 * import java.util.ArrayList; import java.util.Collections;
 * 
 * public class PokerHand {
 * 
 * public ArrayList<Card> cards; // boolean[] handCheck; // hold true false for
 * which legal hand // this.PokerHand // object is // int[] handCheckRanks; //
 * hold integer values that are from handCheck for // comparisons. int
 * valueOfHighestHandScore; // this value will be initialized when a hand
 * LegalHandEnum legalize; // is valid and will contain the highest // value. //
 * boolean isRoyalFlush, isStraightFlush, is4ofaKind, isFullHouse, isFlush, //
 * isStraight, is3ofaKind, is2Pair, is1Pair; // these booleans help me determine
 * what the hand is during method checks...
 * 
 * Card highCard;
 * 
 * // Construct one PokerHand // Precondition: All five cards are unique
 * 
 * public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
 * 
 * cards = new ArrayList<Card>();
 * 
 * cards.add(c1); // index 0 cards.add(c2); cards.add(c3); cards.add(c4);
 * cards.add(c5); // index 4 Collections.sort(cards); highCard = cards.get(4);
 * // IF if (this.hasOnePair()) { valueOfHighestHandScore = highCard.get(4);
 * legalize = LegalHandEnum.is1pair; } if(this.hasTwoPair(ph)) /* handCheck =
 * new boolean[9]; handCheckRanks = new int[9]; for (int x = 0; x <
 * handCheck.length; x++) { // initialize all values to handCheck[x] = false;
 * handCheckRanks[x] = -1; } if (this.hasOnePair(this) != -1) { handCheck[0] =
 * true; handCheckRanks[0] = this.hasTwoPairLow(this);
 * System.out.println("Hand " + this.toString() + " has OnePair");
 * 
 * }
 * 
 * if (this.hasTwoPairLow(this) != -1) { handCheck[1] = true; handCheckRanks[1]
 * = this.hasTwoPairLow(this); System.out.println("Hand " + this.toString() +
 * " has TwoPair");
 * 
 * } if (this.hasThreeOfAKind(this) != -1) { handCheck[2] = true;
 * handCheckRanks[2] = this.hasThreeOfAKind(this); System.out.println("Hand " +
 * this.toString() + " has Three of a Kind");
 * 
 * } if (this.hasStraight(this) != -1) { handCheck[3] = true; handCheckRanks[3]
 * = this.hasStraight(this); System.out.println("Hand " + this.toString() +
 * " has Straight");
 * 
 * } if (this.hasFlush(this) != -1) { handCheck[4] = true; handCheckRanks[4] =
 * this.hasFlush(this); } if (this.hasFullHouse(this) != -1) { handCheck[5] =
 * true; handCheckRanks[5] = this.hasFullHouse(this); } if
 * (this.hasFourOfAKind(this) != -1) { handCheck[6] = true; handCheckRanks[6] =
 * this.hasFourOfAKind(this); } if (this.hasStraightFlush(this) != -1) {
 * handCheck[7] = true; handCheckRanks[7] = this.hasStraightFlush(this); } if
 * (this.hasRoyalFlush(this) != -1) { handCheck[8] = true; handCheckRanks[8] =
 * this.hasRoyalFlush(this); }
 * 
 * 
 * }
 * 
 * public Card getCard(int index) { if (index >= 0 && index <= 4) { return
 * this.cards.get(index); } else return null; }
 * 
 * public int tieBreaker(PokerHand ph) { for (int i = 4; i >= 0; i--) { int
 * compThis = this.cards.get(i).getRank(); int compOther =
 * ph.cards.get(i).getRank(); if (compThis != compOther) return compThis -
 * compOther; } return 0; }
 * 
 * public static void checkEqualCards(Card c1, Card c2, Card c3, Card c4, Card
 * c5) throws DuplicateCardException { if (c1.equals(c2) || c1.equals(c3) ||
 * c1.equals(c4) || c1.equals(c5)) { // throw DuplicateCardException; } if
 * (c2.equals(c3) || c2.equals(c4) || c2.equals(c5)) {
 * 
 * } if (c3.equals(c4) || c3.equals(c5)) {
 * 
 * } if (c4.equals(c5)) {
 * 
 * }
 * 
 * }
 * 
 * // Get the rank of any of the five cards // Precondition: index >= 0 and
 * index <=4 private int getValue(int index) { if (index >= 0 && index <= 4) {
 * return cards.get(index).getRank(); } else return -1; // as an error statement
 * if someone is deliberately // trying to break the code! }
 * 
 * // Return true if there are exactly two pairs in this hand // Precondition:
 * cards is sorted. /* public boolean hasTwoPair() { int numPairs = 0; for (int
 * i = 0; i < cards.size() - 2; i++) { if (cards.get(i) == cards.get(i + 1))
 * numPairs++; } return numPairs == 2; }
 * 
 * 
 * /**
 * 
 * @param other The PokerHand to be compared to this
 * 
 * @return Return 0 if this and other are tied (have two pair and the ranks of
 * the other fifth card value are equal. Return +1 if this is a better hand than
 * other. Or return -1 if other is a better hand than this.
 * 
 * Precondition: this and other both have a valid two-pair hand with all unique
 * Cards
 * 
 * public int compareTo(PokerHand ph) { int indexOfThisHand =
 * findTrue(this.handCheck); int indexOfOtherHand = findTrue(ph.handCheck); if
 * ((indexOfThisHand - indexOfOtherHand) == 0) { return this.tieBreaker(ph); }
 * return (indexOfThisHand - indexOfOtherHand); }
 * 
 * public int findTrue(boolean[] whatHand) { // last index of boolean array for
 * (int count = whatHand.length - 1; count >= 0; count--) { if (whatHand[count])
 * { return count; // Return the highest value of true. } } return -1; // if it
 * is none of the valid hands, return }
 * 
 * public int hasRoyalFlush(PokerHand ph) {
 * 
 * Suit suitCheck = cards.get(0).getSuit(); int flushChecker = 10; int handIndex
 * = 0; while (flushChecker < 15) { // 14 is max value if
 * (ph.cards.get(handIndex).getRank() == flushChecker) { if
 * (ph.cards.get(handIndex).getSuit().equals(suitCheck)) { flushChecker++;
 * handIndex++; } else return -1; } else return -1; }
 * 
 * return ph.cards.get(4).getRank(); // if you made it this far, it's //
 * definitely a royal flush.
 * 
 * }
 * 
 * public int hasStraightFlush(PokerHand ph) { Suit suitCheck =
 * cards.get(0).getSuit(); int rankChecker = cards.get(0).getRank(); /* special
 * cases: Ace High vs Ace Low and how to model it: 1) if set contains 2,3,4,5,A
 * as a special model
 * 
 * // check for AceLow straights first as an edge case: if
 * (ph.cards.get(0).getRank() == 2 && ph.cards.get(4).getRank() == 14) { // if
 * // OFF // THE // BAT // you // know // that // there's // a // 2 // and // an
 * // A int aceLowCounter = 3; boolean trustTheProcess = true; for (int index =
 * 1; index < 4; index++) { // check the cards in the // middle to see if it's
 * // a straight flush
 * 
 * if (ph.cards.get(index).getRank() == aceLowCounter &&
 * ph.cards.get(index).getSuit().equals(suitCheck)) { aceLowCounter++; } else {
 * trustTheProcess = false; } }
 * 
 * if (trustTheProcess) { // if you can trust the process, Card temp =
 * ph.cards.remove(4); // grab the Ace ph.cards.add(0, temp); return
 * ph.cards.get(3).getRank(); // Return the highest value in // the Ace Low
 * Straight, // which should be 5... // 2,3,4,5,A }
 * 
 * } int handIndex = 0; while (handIndex < 5) { if
 * (ph.cards.get(handIndex).getRank() == rankChecker) { if
 * (ph.cards.get(handIndex).getSuit().equals(suitCheck)) { rankChecker++;
 * handIndex++; } else return -1; } else return -1; } return
 * ph.cards.get(4).getRank(); // if you made it this far, it's // definitely a
 * StraightFlush, not // AceLow, so return the highest // value. }
 * 
 * private int indexOfRank(int x, PokerHand ph) { for (int i = 0; i < 5; i++) {
 * if (ph.cards.get(i).getRank() == x) { return i; } } return -1; }
 * 
 * public int hasFullHouse(PokerHand ph) { int threeRank = hasThreeOfAKind(ph);
 * int pairRank = hasOnePair(ph);
 * 
 * boolean isFullHouse = false; if (threeRank != -1 && pairRank != -1) {
 * isFullHouse = true; } if (isFullHouse) { if (pairRank > threeRank) { int
 * indexOf2Pair = indexOfRank(pairRank, ph); if (indexOf2Pair != 0) { Card temp1
 * = ph.cards.remove(indexOf2Pair); Card temp2 = ph.cards.remove(indexOf2Pair +
 * 1); ph.cards.add(0, temp1); ph.cards.add(0, temp2); } } return threeRank; }
 * return -1; }
 * 
 * public int hasFlush(PokerHand ph) { Suit coatCheck =
 * ph.cards.get(0).getSuit(); boolean trustTheProcess = true; for (int index =
 * 0; index < 5; index++) { if (ph.cards.get(index).getSuit() != coatCheck) {
 * trustTheProcess = false; } } if (trustTheProcess) { return
 * ph.highCard.getRank(); // throw back the highest value in the // flush, as
 * this could be useful in // comparisons! } return -1; }
 * 
 * public int hasStraight(PokerHand ph) { int rankChecker =
 * cards.get(0).getRank(); // this straight must // increment from //
 * rankchecker /* special cases: Ace High vs Ace Low and how to model it: 1) if
 * set contains 2,3,4,5,A as a special model
 * 
 * // check for AceLow straights first as an edge case: if (rankChecker == 2 &&
 * ph.highCard.getRank() == 14) { int aceLowCounter = 3; boolean trustTheProcess
 * = true; for (int index = 1; index <= 3; index++) { if
 * (cards.get(index).getRank() == aceLowCounter) { aceLowCounter++; } else {
 * trustTheProcess = false; } }
 * 
 * if (trustTheProcess && ph.cards.get(3).getRank() == 5) { return
 * ph.cards.get(3).getRank(); }
 * 
 * } int handIndex = 0;
 * 
 * while (handIndex < 5) { // 14 is max value if (cards.get(handIndex).getRank()
 * == rankChecker) { rankChecker++; handIndex++; } else // if this test fails
 * even once, it will return -1 return -1; } /* this code can be implemented IF
 * wrap around checks are necessary. ie: if Q K A 2 3 is a straight
 * if(rankChecker == 15) //achieve wrap-around checks. rankChecker = 2;
 * 
 * return highCard.getRank(); // return the rank of the highest value in // the
 * straight }
 * 
 * public int hasFourOfAKind(PokerHand ph) { for (int i = 0; i < 2; i++) { if
 * (ph.getValue(i) == ph.getValue(i + 1) && ph.getValue(i + 1) == ph.getValue(i
 * + 2) && ph.getValue(i + 2) == ph.getValue(i + 3)) { return ph.getValue(i); }
 * } return -1; }
 * 
 * public int hasThreeOfAKind(PokerHand ph) { for (int i = 0; i < 3; i++) { if
 * (ph.getValue(i) == ph.getValue(i + 1) && ph.getValue(i + 1) == ph.getValue(i
 * + 2)) { return ph.getValue(i); } } return -1; }
 * 
 * public int hasTwoPairLow(PokerHand ph) { for (int i = 0; i < 4; i++) { if
 * (ph.getValue(i) == ph.getValue(i + 1)) { try { if (ph.getValue(i + 1) !=
 * ph.getValue(i + 2)) { return ph.getValue(i); } } catch
 * (IndexOutOfBoundsException e) { } } }
 * 
 * return -1; }
 * 
 * public boolean hasTwoPairHigh(PokerHand ph) { for (int i = 4; i > 0; i--) {
 * if (ph.getValue(i) == ph.getValue(i - 1)) { try { if (ph.getValue(i - 1) !=
 * ph.getValue(i - 2)) { // make sure // it's not // a 3pair // by // accident!
 * return ph.getValue(i); } } catch (IndexOutOfBoundsException e) { }
 * 
 * } } return 0; }
 * 
 * public boolean hasOnePair() { for (int i = 0; i < 4; i++) { if(i > 0) { Card
 * isSame = ph.cards.get(i-1) } try { if (ph.cards.get(i).getRank() ==
 * ph.cards.get(i + 1).getRank() //two in a row from i to i +1 && ph.cards.get(i
 * + 1) != ph.cards.get(i + 2)) { //not 3 in a row legalize =
 * LegalHandEnum.is1Pair; return true; } } catch (ArrayIndexOutOfBoundsException
 * e) { }
 * 
 * } return false; }
 * 
 * 
 * // Provide a textual version of this PokerHand public String toString() {
 * String result = ""; result += cards.get(0) + " "; result += cards.get(1) +
 * " "; result += cards.get(2) + " "; result += cards.get(3) + " "; result +=
 * cards.get(4); return result; }
 * 
 * }
 */
