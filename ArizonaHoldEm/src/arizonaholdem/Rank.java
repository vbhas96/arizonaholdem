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

public enum Rank {
	 DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(
			12), KING(13), ACE(14);
	
	private int VALUE;

	Rank(int value) {
		this.VALUE = value;
	}
        public String toString(){
            if(this.VALUE == 11){
                return "J";
            }
            else if(this.VALUE == 12){
                return "Q";
            }
            else if(this.VALUE == 13){
                return "K";
            }
            else if(this.VALUE == 14){
                return "A";
            }
            else
                return "" + getValue();
        }
	public int getValue() {
		return VALUE;
	}

}
