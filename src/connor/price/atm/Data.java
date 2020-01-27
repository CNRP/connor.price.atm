package connor.price.atm;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Data {
	static JSONArray json = null;
	static String file = "accounts.json";
	
	static void getJson(){
		try {
	    	JSONParser parser = new JSONParser();
	    	Object obj = parser.parse(new FileReader(file));
	    	json = (JSONArray) obj;
	    	
	    } catch (IOException | ParseException e) {
	          e.printStackTrace();
	    }
	}
	
	static void setJson() throws IOException{
		FileWriter fr = (new FileWriter(file));
		try {
			fr.write(json.toJSONString());
//			System.out.println("Successfully Copied JSON Object to File...");
//			System.out.println("\nJSON Object: " + json);
		} catch (IOException e) {
	          e.printStackTrace();
	    }finally {
	    	fr.flush();
	    	fr.close();
	    }
	}
}
