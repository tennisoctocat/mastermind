/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author Cynthia Hom
 *  @since October 3, 2017
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) {
	//	exactMatches = partialMatches = 0;
		pegs = new Peg[numPegs];
		//initialize each peg to default values
		for(int index = 0; index < numPegs; index++)
		{
			pegs[index] = new Peg();
		}
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) 
	{ 
		//loop through both arrays, comparing values and 
		//incrementing the number to return when values are same
		for (int index = 0; index < master.pegs.length; index++)
		{
			if (isExactMatch(master, index))
			{
				exactMatches++;
			}
		}
		return exactMatches; 
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	/*
	public int getPartialMatches(PegArray master) 
	{
		System.out.println("\n\nin getPartialMatches");
		char toCompare = '0';	//char value of peg to compare to master
		
		//values of master and guess arrays at different indicies than toCompare
		char masterChar = '0', guessChar = '0';	
		
		boolean isPartialMatch = false;	
		
		//loop through both arrays, comparing values and 
		//incrementing the number to return when values are not same
		//and there is another unmatched value in the array
		for (int index = 0; index < master.pegs.length; index++)
		{
			toCompare = pegs[index].getLetter();
			System.out.println("in first for loop, index is " + index);
			System.out.println("toCompare is " + toCompare);
			//if char is not an exact match
			if(master.pegs[index].getLetter() != toCompare)
			{
				System.out.println("char not an exact match");
				//loop through and compare to other values
				for (int num = 0; num < master.pegs.length && !isPartialMatch; num++)
				{
					masterChar = master.pegs[num].getLetter();
					guessChar = pegs[num].getLetter();
					System.out.println("\nin second for loop, num is " + num);
					//if there is a char at another index that matches the 
					//guess char, increment the number of partial matches 
					//only if the master's char is not an exact match of the
					//char at the corresponding index in the guess
					System.out.println("master char is " + masterChar);
					System.out.println("value at guess array is " + guessChar);
					System.out.println("toCompare is " + toCompare);
					isPartialMatch = (toCompare == masterChar) && (masterChar != guessChar);
					System.out.println("if chars match is " +  (toCompare == masterChar));
					System.out.println("not exact match is " +  (masterChar != guessChar));
					if (isPartialMatch)
					{
						partialMatches++;
						System.out.println("partialMatches++");
					}
						
				}
			}
		}
		return partialMatches;
	}
	*/

	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) 
	{
		//array for whether or not the char at the index of the master 
			//array is matched with a char at the guess array already
			//true if already matched
		boolean [] isMatched = new boolean[master.pegs.length];

		//initialize array
		for(int i = 0; i < isMatched.length; i++)
		{
			isMatched[i] = false;	
		}

		//loop through values to determine if each is a partial match
		for(int index = 0; index < master.pegs.length; index++)
		{
			//if exact match, store false in partial match array, but 
				//make value in isMatched true so we know the value at
				//the master array for this index has already been matched
			//otherwise check to see if its a partial match
			if (isExactMatch(master, index))
			{
				isMatched[index] = true;
			}
			else
			{
				//for each value that was not an exact match, look
					//through other values in master array that are
					//unmatched to try to find a partial match
				for(int num = 0; num < master.pegs.length; num++)
				{
					//check if a partial match is found:
					//if the two array's values match
						//and the value of the master array is not already 
						//counted as a partial match for another index and 
						//the value of master at this index is not an exact 
						//match
					if ((master.pegs[num].getLetter() == 
						pegs[index].getLetter()) && !isMatched[num] 
						&& !isExactMatch(master, num))
					{
						partialMatches++;

						//make the value in isMatched
						//true so the value at that index in master
						//is not counted as another partial match
						isMatched[num] = true;

						//to quit loop
						num = 5;	
					}
				}
			}
		}
		return partialMatches;
	}

	/**
	* Determines whether there is an exact match at the specified
	* index between master and the peg array of the PegArray object
	* @param PegArray master 	the master array to compare to
	* @param int index 			the index for which to compare values
	* @return 					true if an exact match, false if not
	*/
	private boolean isExactMatch(PegArray master, int index)
	{
		return master.pegs[index].getLetter() == pegs[index].getLetter();
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
