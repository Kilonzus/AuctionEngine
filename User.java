//*******************************************************************
// User.java
// Peter Kilonzo
// 22-March-2019
//*******************************************************************

import java.util.*;
import java.io.*;
import java.security.*;

// User.java
/**
* This class is for the user and holds all the data that the user would need
* Future implementations would include a stronger hash algorithm (MD5 is vunerable)
* as well as a method to log into the system
 */
public class User {
	private boolean loggedin;  //value to see if user is logged in perhaps a little elemetary
	private String username;
	private String password;
	private ArrayList<String> allBids = new ArrayList<String>(); //I use arraylist because of its dynamic abilities
	//public static void login();

	/** parameterized constructor method
 	* @param String usern - the specified username for the new user
 	* @param String passw - the password the user wants to use to login
 	*This method creates a user by taking the username and password
 	*the password is immeadiately hashed to ensure we don't keep plaintext
 	*A stronger has than MD5 is definitely needed */
	public User(String usern, String passw) {
		username = usern;
		password = shPass(passw);
		loggedin = false;
	}

	/** password hashing method
 	* @param String pm - password to be hashed
 	* @return a hashed string of the password
 	*This method hashes the password given for creation and authentification
 	*an alternative that i would consider is pbkdf2, note this is not salted
 	*either so ideally it would be salted and hashed with a good algorithm */

	public String shPass(String pm) {
		//byte [] passByte = pm.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); //specify algorithm
			md.reset();
			md.update(pm.getBytes()); //hashes bytes f string
			byte[] hpass = md.digest();
			StringBuffer s = new StringBuffer();
			for(byte h1 : hpass) {
				s.append(Integer.toHexString(h1).toString()); 
			}
			return s.toString();
			//return hpass;
		} catch(Exception t) { //exception to catch if algo isn't found
			return null;
		}

	}
	 /** update history method
 	* @param String - name of item bid on
 	*this methods upates the bid history of the user, however if a user bids
 	*multiple times it adds the same item twice, i was going to remove this 
 	*however the user may want the data of how much they have bid on a certain
 	*item so i left it in there
 	*/
	public void updateHis(String name){
		allBids.add(name);

	}
	 /**print history method
 	* @param none
 	*this method prints the history of the users bids
 	*/
	public void printHistory() {
		
		for (int i = 0; i < allBids.size(); i++) {
			System.out.println("User " + username + " has bid on " +allBids.get(i));
		}
	}
	 /**print history method
 	* @param none
 	* @return the username of the user
 	*this method returns the username
 	*/
	public String getName() {
		
		return username;
	}
	 /**isLogin method
 	* @param none
 	* @return true or false value if user logged in
 	*this method returns true ifthe user is logged in false if not
 	*/
	public boolean isLogin() {
		return loggedin;
	}

 /** main method
 * @param args String[] */
public static void main(String[] args) {
    User tuse = new User("test", "Password");
  }
}