/**
 *	Plays the game of MasterMind.
 *	The computer creates a code of 4 pegs in a certain order. The 
 * 	user has 10 guesses to determine the order. After each guess,
 * 	the computer tells the user how many "exact" matches (correct
 * 	location and value of peg) and "partial" matches (correct value
 * 	but not location). The peg values are letters A through F.
 *
 *	@author Cynthia Hom
 *	@since October 3, 2017
 */

public class MasterMind {

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F

	/**
	 * Constructor 
	 */
	 public MasterMind()
	 {
		 reveal = false;
		 guesses = new PegArray[MAX_GUESSES];
		 //initialize all PegArray objects in the guesses array
		 for(int index = 0; index < guesses.length; index++)
		 {
		 	guesses[index] = new PegArray(PEGS_IN_CODE);
		 }
		 master = new PegArray(PEGS_IN_CODE);
	 }
	 
	/**
	 * main to run the playGame method
	 */
	public static void main(String [] args)
	{
		MasterMind masterMind = new MasterMind();
		masterMind.playGame();
	}
	
	/**
	 * Calls a sequence of commands to play the game, including
	 * calls to other methods at appropriate times
	 */
	public void playGame()
	{
		int turnNum = 0;	//number of turn that user is on

		boolean gameOver = false;	//if exact matches is 4
		
		//generate random key
		makeMaster();

		//testing________
	//	System.out.println("Master is " + master.getPeg(0).getLetter() + master.getPeg(1).getLetter() + 
	//s	master.getPeg(2).getLetter() + master.getPeg(3).getLetter() );
		
		//start game
		printIntroduction(); 
		Prompt.getString("Hit the Enter key to start the game");
		printBoard();
		
		//loop for turns: until exact matches is 4 or user runs out 
			//of guesses
		for(turnNum = 1; turnNum <= 10 && !gameOver; turnNum++)
		{
			playATurn(turnNum);
			gameOver = guesses[turnNum - 1].getExact() == 4;
		}

		//print end game message corresponding to user's performance
		if(turnNum == 11)
			System.out.println("\nOops. You were unable to find the " 
				+ "solution in 10 guesses.");
		else
			System.out.println("\nNice work! You found the master code "
				+ "in " + (turnNum - 1) + " guesses.");
	}

	/**
	* Plays one turn/guess of the game by calling methods to get input 
	* from the user and use the input to determine the exact/partial
	* matches
	* @param int turnNum	the number of the guess/turn
	*/
	public void playATurn(int turnNum)
	{
		System.out.println("\nGuess " + turnNum + "\n");

		//get guess from user
		storeInput(getInput(), turnNum);
		
		//evalate number of exact and partial matches
		guesses[turnNum - 1].getExactMatches(master);
		guesses[turnNum - 1].getPartialMatches(master);
		
		printBoard();
	}
	
	/**
	 * Generates a random array of 4 pegs for the master peg array
	 * by using roll() method in dice class
	 */
	public void makeMaster()
	{
		char randomLetter = '#';
		Die die = new Die();
		//loop through, assigning random roll() value after converting
		//it to a letter
		for(int index = 0; index < PEGS_IN_CODE; index++)
		{
			randomLetter = (char)(die.roll() + 'A' - 1);
			master.getPeg(index).setLetter(randomLetter);
		}
	}

	/**
	* Accepts user input of a code using letters A through F
	* Lowercase, uppercase, and mixed case allowed
	* Asks user again if input is not 4 letters long or 
	* characters are not from A through F
	* @return 	the valid user input
	*/
	public String getInput()
	{
		boolean badInput = false;	//true if input does not meet
											//requirements
		String userInput = "";
		do
		{
			//print error message to ask user to try again
			if(badInput)
			{
				System.out.println("ERROR: Bad input, try again.");
			}

			//get and manipulate user input
			userInput = Prompt.getString("Enter the code using "
					+ "(A,B,C,D,E,F). For example, "
					+ "ABCD or abcd from left-to-right");
			userInput = userInput.toUpperCase();

			//update value of bad input
			badInput = !isBetweenAandF(userInput) || 
						(userInput.length() != PEGS_IN_CODE);
		} while(badInput);
		return userInput;
	}

	/**
	* Stores the user input as characters in the peg array of
	* the PegArray object at the PegArray[] index that corresponds
	* to the guess number
	* @param String pegLetters	the string with the peg chars
	* @param int guessNum 		the number of the guess
	*/
	public void storeInput(String pegLetters, int guessNum)
	{
		//loop through each index of the string
		for(int index = 0; index < pegLetters.length(); index++)
		{
			//subtract 1 because guess 1 is at index 0
			guesses[guessNum - 1].getPeg(index).setLetter(pegLetters.charAt(index));
		}

	}

	/**
	* Determines if all chars in user input is between A and F
	* @param String input	The string to check
	* @return boolean btwnAandF 	true if all chars are between A and F
	*/
	public boolean isBetweenAandF(String input)
	{	
		boolean btwnAandF = true;
		//loop through, check each char's ascii
		for(int index = 0; index < input.length(); index++)
		{
			if(input.charAt(index) > 'F' || input.charAt(index) < 'A')
				btwnAandF = false;
		}
		return btwnAandF;
	}
	
	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}