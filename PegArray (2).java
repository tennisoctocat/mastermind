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
			if (master.pegs[index].getLetter() == pegs[index].getLetter())
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
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
