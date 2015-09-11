/*
 * File: Hangman.java
 * ------------------
 * Name: Hayan Khan
 * 
 * Stanford Programming Methodology Assignment #4
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void init() { //initializes canvas prior to the run function
    	canvas = new HangmanCanvas();
    	add(canvas);
   	}

    public void run() {
    	setup();
    	playHangman();
    }

    //lets the user know the amount of letters in the word by displaying a series of dashed lines on the screen
    private void setup(){
    	canvas.reset();
    	println("Welcome to Hangman");
    	hangmanWord = new HangmanLexicon();
    	//sets randomness in choosing a word
    	rgen = RandomGenerator.getInstance();
    	int numWords = hangmanWord.getWordCount();
    	//makes it so the program takes in consideration all the words on the text file.
    	int wordNum = rgen.nextInt(0, numWords);
    	word = hangmanWord.getWord(wordNum);
    	for (int i=0 ; i<word.length(); i++){
			updatedWord = updatedWord + "-";
			numLetters++;
		}
    	canvas.displayWord(updatedWord);
    }
    
    private void playHangman(){ //ensure that only one letter is used as input also only capital letters are input
    	for (numGuesses = 8 ; numGuesses> 0 ; numGuesses--){
    		if (numLetters != 0){
    			guessInWord = false; // assumption that guess is not in the word
    			println("The word looks like this:" + updatedWord );
    			println("You have have " + numGuesses + " guesses left");	
    			String lineEntered = readLine("Your guess:");
    			while (lineEntered.length() != 1){// makes sure that input is only a single value
    				lineEntered = readLine("Invalid guess, please reguess with a single character:");
    			}
    			guess = lineEntered.charAt(0);
    			convertGuessToUpper(); //converts lower case character guesses into uppercase
    			checkGuess(); 
    		} else if (numLetters == 0){
    			println("You guessed the word " + word + ".");
    			println("You win.");
    			break;
    		}
    	 }
    	checkIfGameLossed();
    }
    
    //if the user enters a guess that is a lower case letter, this function converts it to an uppercase letter so that it can be read by the program
    private void convertGuessToUpper(){
    	if (Character.isLowerCase(guess)){
    		guess = Character.toUpperCase(guess);
    	}
    }
    
    /*
     * Reads user input and checks whether the guess matches any of the letters.
     * If the guess does then, all the values of that guess are revealed on the canvas,
     * however if the guess is wrong, a body part is hung. As the game progresses, the
     * user is updated with instructions.
     */
    private void checkGuess(){
    	for (int i = 0 ; i < word.length() ; i++){
    		if (word.charAt(i)== guess){
    			updatedWord = updatedWord.substring(0,i) + guess + updatedWord.substring(i+1);	//creates updated word after guess
    			numLetters--;
    			guessInWord = true;
    		}
    	}
    	if (guessInWord){
    		numGuesses++;
    		lettersGuessed +=guess;
    		println("That guess is correct.");
    		canvas.displayWord(updatedWord);
    	} else {
    		println("There are no " + guess + "'s in the word");
    		canvas.noteIncorrectGuess(guess);
    	}
    }
    
    // checks whether the game is lost
    private void checkIfGameLossed(){
    	if (numLetters !=0){
    		println("You're completely hung.");
			println("The word was:" + word );
			println("You lose.");
    	}
    }
    
    // private instance variables
	private String word;
	private String updatedWord = "";
	private String lettersGuessed = ""; 
	private int numGuesses, numLetters = 0;
	char guess;
	boolean guessInWord;
	private HangmanCanvas canvas;
	private HangmanLexicon hangmanWord;
	private RandomGenerator rgen;
}
