package connor.price.atm;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

	static Scanner s = new Scanner(System.in);
	static String loggedIn = "0000000";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {		
	    
		Data.getJson();
	    logIn();
	}
	
	public static void logIn() throws IOException {
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Welcome");
		loggedIn = "0000000";
		System.out.print("Please enter your Account No:  ");
		String accountNo = s.nextLine();
		
		System.out.print("Please enter your Pin No:  ");
		String pinNo = s.nextLine();

	    for (Object o : Data.json) {
	        JSONObject account = (JSONObject) o;
	        if(account.get("pin-no").toString().equals(pinNo) && account.get("account-no").toString().equals(accountNo)) {
	        	loggedIn = account.get("account-no").toString();
	        	break;
	        }
	    }
	    
	    if(loggedIn != "0000000") {
	    	loggedIn();
	    }else {
	    	
	    	logIn();
	    }
	}

	public static void loggedIn() throws IOException{
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Main Menu");
		System.out.println("		1 - View Balance");
		System.out.println("		2 - Withdraw Cash");
		System.out.println("		3 - Deposit Funds");
		System.out.println("		4 - Exit");
		System.out.print("				Enter Your Choice: ");
		String choice = s.nextLine();
		switch (choice) {
			case "1":
				viewBalance();
				break;
			case "2":
				withdrawMenu();
				break;
			case "3":
				System.out.println("3");
				break;
			case "4":
				System.out.println("4");
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void withdrawMenu() throws IOException{
		System.out.println("\n\n\n\n\n\n\n\n");
		int amount = 0;
		
		System.out.println("Withdraw Menu");
		System.out.println("	  1 - £20     4 - £100");
		System.out.println("	  2 - £40     5 - £200");
		System.out.println("	  3 - £60     6 - Cancel");
		System.out.print("				Enter Your Choice: ");

		String choice = s.nextLine();
		
		switch (choice) {
			case "1":
				amount = 20;
				break;
			case "2":
				amount = 40;
				break;
			case "3":
				amount = 60;
				break;
			case "4":
				amount = 100;
				break;
			case "5":
				amount = 200;
				break;
			case "6":
				amount = 0;
				break;
			default: 
				amount = 0;
				break;
		}
		
	    for (Object o : Data.json) {
	        JSONObject account = (JSONObject) o;
	        if(account.get("account-no").toString().equals(loggedIn)) {
	        	
	        	Double balance = (Double) account.get("balance");
	        	account.put("balance", balance-amount);
	        	Data.setJson();
	    		
	        	break;
	        }
	    }
		
	} 
	
	public static void viewBalance() throws IOException{
		System.out.println("\n\n\n\n\n\n\n\n");
	    for (Object o : Data.json) {
	        JSONObject account = (JSONObject) o;
	        if(account.get("account-no").toString().equals(loggedIn)) {
	        	Double balance = (Double) account.get("balance");
	        	
	        	System.out.println("");
	        	System.out.println("Your account balance is: "+balance);
	        	System.out.println("\n");
	        	System.out.println("	  1 - YES     2 - NO");
	        	System.out.println("\n");
	    		System.out.print("Do you wish to make another transaction: ");
	    		String choice = s.nextLine();
	    		
	    		switch (choice) {
				case "1":
					loggedIn();
					break;
				case "2":
					logIn();
					break;
				default: 
					
					break;
	    		}
	        	break;
	        }
	    }
	}
}
