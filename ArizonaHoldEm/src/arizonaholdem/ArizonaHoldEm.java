/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arizonaholdem;
import java.util.Scanner;

/**
 *
 * @author vbhas
 */
public class ArizonaHoldEm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Welcome to Arizona Hold 'Em! How many players are playing today? \n Type an integer value between 2-10!");
		int x = Integer.parseInt(sc.nextLine());
		Dealer deal = new Dealer(x);
		System.out.println("Thanks for playing! Come again soon!");
		sc.close();
	}
}
