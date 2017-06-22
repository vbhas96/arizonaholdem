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
public enum Suit {
  CLUBS('\u2663'), DIAMONDS('\u2666'), HEARTS('\u2665'), SPADES('\u2660');
	private char VALUE;
	Suit(char val){
		this.VALUE = val;
	}
	public String toString(){
		return "" + this.VALUE;
	}
}
  