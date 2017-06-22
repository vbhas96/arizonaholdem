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

public class Card implements Comparable<Card>{
  Rank cRank;
  Suit cSuit;
  public Card(Rank inputRank, Suit inputSuit){
	  this.cRank = inputRank;
	  this.cSuit = inputSuit;
  }
  public int getRank(){
	  return this.cRank.getValue();
  }
  public Suit getSuit(){
	  return cSuit;
  }
  public boolean equals(Card other){
	  if(this.sameSuit(other) && this.sameRank(other)){
		  return true;
	  }
	  else
		  return false;
  }
  public boolean sameSuit(Card other){
	  if(cSuit.equals(other))
		  return true;
	  else
		  return false;
  }
  public boolean sameRank(Card other){
	  if(this.getRank() == other.getRank())
		  return true;
	  else
		  return false;
		  
  }
  public int compareTo(Card other) {
	int thisVal = this.cRank.getValue();
	int otherVal = other.cRank.getValue();
	
	return thisVal-otherVal;  //+ means this is bigger, - means this is smaller
	
	//At this point, if 0, the rank values are equivalent.
	
  }
  public String toString(){
	  return cRank.toString() + cSuit.toString();
  }
}