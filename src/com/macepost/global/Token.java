package com.macepost.global;

import java.util.ArrayList;
import java.util.Random;

public class Token {
	

	
	private static ArrayList<String> tokens = new ArrayList<>();
	
	public String getToken() {
		String token = randString();
		tokens.add(token);
		return token;
	}
	
	public boolean checkToken(String token) {
		if(tokens.contains(token)) {
			removeToken(token);
			return true;
		}
		return false;
	}
	
	public void removeToken(String token) {
		tokens.remove(token);
	}
	
	public String randString() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 64;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    return generatedString;
	}
}
