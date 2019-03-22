//*******************************************************************
// Auction.java
// Peter Kilonzo
// 22-March-2019
//*******************************************************************

import java.util.*;
import java.io.*;
import java.lang.*;

// Auction.java
/**
* This class is for the auction and the main method here is what we will
* use to test the core functionalities of our program
 */
public class Auction {
	private String name;
	private float price;
	private List<List> bidHistory = new ArrayList<List>(); // this variable is slightly upappleaing, i didn't specify a type for the nested List so i could thro diff data types in there
	private float stepsize;  //i included this value because a bid of $1 million should not be outdone by a bid of $1 million and one cent, it would take forever
	private User cWinner; // current winner of bid

	/** place new bid method
 	* @param User m - the user who is bidding
 	* @param float bid - the amount the user has bid on the item
 	*This method creates a new bid for a user of an item of their choosing
 	*This is where you can see it will call the user updatHis function multiple times
 	*/
	public void newBid(float bid, User m) {
		if((bid-price) < (stepsize)) {
			System.out.printf("Your bid must be greater than %f\n", stepsize + price);
			throw new IllegalArgumentException(); // I throw an exception if the bid is too low
		}
		if (bid > price) {
			price = round(bid);
			stepsize = round(bid * (float) 0.035); // i choose 3.5 percent as my minumum increase price i.e. user must bid 3.5% more than current price
			updateHistory(bid, m);
			cWinner = m;
			m.updateHis(name);
		}
	}

	/** parameterized constructor method
 	* @param String des - Short escription of the item to be bid on
 	* @param float curr - starting price of bid
 	*This method creates a new auction
 	*/
	public Auction(String des, float curr) {
		name = des;
		price = round(curr);

		stepsize = round(curr * (float) 0.035);
	}

	 /** update history method
 	* @param float bid - amount that was bid on the item
 	* @param User m - user that placed bid for that amount
 	*this methods upates the bid history of the Auction, however a couple thing are going on here
 	* the Array list that the info is going into is 2-D however the nested list is not type defined
 	* this is so ican add both a string(the name) and float(the bid) to the list
 	* the compiler screams at me but it works for now lol
 	*/
	public void updateHistory(float bid, User m) {
		List x = new ArrayList();
		x.add(m.getName());
		x.add(bid);
		bidHistory.add(x);

	}

	/** round method
 	* @param float number - number to be rounded
 	* @return a float that is rounded to 2 decimal places
 	*this methods round my float to two decimal places
 	*tbh i found it online and tweaked it a bit to fid my precision 
 	*/
	public static float round(float number) {
    	int pow = 10;
    	for (int i = 1; i < 2; i++) {
        	pow *= 10;
    	}
    	float tmp = number * pow;
    	return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
	}

	 /**print auction method
 	* @param none
 	* This methods prints out all information for the current auction
 	* from the current winner, price, how much more you have to bid
 	* the name, as well as the history
 	*/
	public void auctionInfo() {
		System.out.println("The item being auctioned is " + name);
		System.out.printf("The current winning bid is %f \n", price);
		System.out.println("The person with the highest bid is " + cWinner.getName());
		System.out.printf("You must bid %f more than the current price\n", stepsize);
		for(int i = 0; i < bidHistory.size(); i++){
			for(int j = 0; j < bidHistory.get(i).size()-1; j++) {
				Object o = bidHistory.get(i).get(j); // this is the name of user
				Object p = bidHistory.get(i).get(j+1); // this is the price they bid

				if(o instanceof String && p instanceof Float) {
					System.out.println("User " + o.toString() +" bid an amount of " + p.toString());
				}
			}
		}

	}
 /** main method
 * @param args String[]
 *this just runs a couple test to make sure all functionality is present */
	public static void main(String[] args) {
    	User tuse = new User("test", "Password"); // creates user tuse
    	User ruse = new User("best", "nosecurity"); // creates user ruse
    	Auction first = new Auction("Bread", (float)2.00); // creates auction for bread
    	first.newBid((float)3.00, tuse); //new bids
    	first.newBid((float)3.13, ruse);
    	first.newBid((float)3.53, tuse);
    	first.newBid((float)4.13, ruse);
    	first.auctionInfo();
    	tuse.printHistory();
 	}


}