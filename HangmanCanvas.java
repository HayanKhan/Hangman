/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		makeWordLabel();
		makeHanger();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
	       guessedWord.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		if (lettersGuessed.length() == 0 ){
			addToGuessedLetters(letter);
		}
		for (int i = 0 ; i < lettersGuessed.length(); i++){
			if (lettersGuessed.charAt(i) == letter) break;
			if (lettersGuessed.length() - 1 == i){
				addToGuessedLetters(letter);
			}
		}
		incorrectGuessNumber++;	
		switch (incorrectGuessNumber){ 
		case 1:
			getHead();
			break;
		case 2:
			getBody();
			break;
		case 3:
			getLeftArm();
			break;
		case 4:
			getRightArm();
			break;
		case 5:
			getLeftLeg();
			break;
		case 6:
			getRightLeg();
			break;		
		case 7:
			getLeftFoot();
			break;
		case 8:
			getRightFoot();
			break;
		}
		
	}
	//creates a label that has the guessed word on the canvas
	private void makeWordLabel(){
		guessedWord = new GLabel("",getWidth()* 0.1,getHeight() * 0.95); 
		guessedWord.setFont("Caibri-BOLD-25");
		add(guessedWord);
	}
	// creates the hanger for the body on the canvas
	private void makeHanger(){
		GLine scaffold = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/4 - ROPE_LENGTH + SCAFFOLD_HEIGHT,getWidth()/2 - BEAM_LENGTH, getHeight()/4 - ROPE_LENGTH);
		add(scaffold);
		GLine beam = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/4 - ROPE_LENGTH,getWidth()/2, getHeight()/4 - ROPE_LENGTH);
		add(beam);
		GLine rope = new GLine(getWidth()/2, getHeight()/4 - ROPE_LENGTH, getWidth()/2, getHeight()/4);
		add(rope);
	}
	// creates a label the show the user all the guesses he has already used
	private void addToGuessedLetters(char guessedLetter){
		lettersGuessed = lettersGuessed + guessedLetter;
		GLabel lettersGuessedLabel = new GLabel(lettersGuessed, getWidth()*0.1, getHeight()*0.9);
		remove(lettersGuessedLabel);
		add(lettersGuessedLabel);
	}
	
	private void getHead(){ // simplify getWidth/2 to center.. etc
		GOval head = new GOval(getWidth()/2 - HEAD_RADIUS, getHeight()/4, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	
	private void getBody(){
		GLine body = new GLine(getWidth()/2,getHeight() / 4 + HEAD_RADIUS * 2, getWidth()/2,HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH );
		add(body);
	}
	
	private void getLeftArm(){
		GLine leftArmPart1 = new GLine(getWidth()/2,getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH,getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		add(leftArmPart1);
		GLine leftArmPart2 = new GLine(getWidth()/2 - UPPER_ARM_LENGTH,getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,getWidth()/2 - UPPER_ARM_LENGTH,getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(leftArmPart2);
	}
	
	private void getRightArm(){
		GLine rightArmPart1 = new GLine(getWidth()/2, getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		add(rightArmPart1);
		GLine rightArmPart2 = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/4 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(rightArmPart2);
	}
	
	private void getLeftLeg(){
		GLine leftLegPart1 = new GLine(getWidth()/2, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH,getWidth()/2 - HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH);
		add(leftLegPart1);
		GLine leftLegPart2 = new GLine(getWidth()/2 - HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH, getWidth()/2 - HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(leftLegPart2);
	}
	
	private void getRightLeg(){
		GLine rightLegPart1 = new GLine(getWidth()/2, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH, getWidth()/2 + HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH);
		add(rightLegPart1);
		GLine rightLegPart2 = new GLine(getWidth()/2 + HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH, getWidth()/2 + HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(rightLegPart2);
	}

	private void getLeftFoot(){
		GLine leftFoot = new GLine(getWidth()/2 - HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH, getWidth()/2 - HIP_WIDTH - FOOT_LENGTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
	}
	
	private void getRightFoot(){
		GLine rightFoot = new GLine(getWidth()/2 + HIP_WIDTH, HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH, getWidth()/2 + HIP_WIDTH + FOOT_LENGTH,HEAD_RADIUS * 2 + getHeight() / 4 + BODY_LENGTH + LEG_LENGTH);
		add(rightFoot);
	}
//private instance variables
	private int incorrectGuessNumber = 0;
	private GLabel guessedWord;
	private String lettersGuessed = ""; 
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}
