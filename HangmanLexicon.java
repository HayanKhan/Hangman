/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;
import acm.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon(){
		try{
		//opens HangmanLexicon.txt
		br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		wordArray = new ArrayList<String>();
		while (true){
			//goes through the text file and one by one reads the name onto the array
			String line = br.readLine();
			if (line == null) break;
			wordArray.add(line);
		}
		br.close();
		}catch (IOException ex){
			throw new ErrorException(ex);
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordArray.size();
	}

/** Goes through word list and returns the word at the specified index. */
	public String getWord(int index) {
		String word = wordArray.get(index);
		return word;
	}
	
	//private instance variables
	private BufferedReader br;
	private ArrayList<String> wordArray;
}
