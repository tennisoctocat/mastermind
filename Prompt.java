import java.util.Scanner;

/**
 *	Prompt.java
 *	Provide some utilities for user input.  We want to enhance the Scanner class,
 *	so that our programs can recover from "bad" input, and also provide a way to
 *	limit numerical input to a range of values.
 *
 *	@author Cynthia Hom
 *	@version August 29, 2017
 */

public class Prompt
{
	/**
	 *	Javadoc comments go here
	 */
	public static String getString (String ask)
	{
		Scanner keyboard = new Scanner( System.in);
		System.out.print(ask + " -> ");
		String input = keyboard.nextLine();
		return input;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	 //need to test this method
	public static char getChar (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		int result = 0;
		do{
			input = getString(ask);
			badInput = input.length() != 1;
		}while(badInput);
		return input.charAt(0);
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static int getInt (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		int result = 0;
		do{
			badInput = false;
			input = getString(ask);
			try {
				result = Integer.parseInt(input);
			}
			catch (NumberFormatException e) {
				badInput = true;
			}
			
		}while(badInput);
		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static int getInt (String ask, int min, int max)
	{
		int intNum = 0;
		boolean numInRange = false;
		do{
			intNum = getInt(ask);
			numInRange = (intNum >= min && intNum <= max);
		}while(!numInRange);
		return intNum;
	}
	
	/**
	 *	returns a double, keeps prompting until user enters in a 
	 * double value
	 */
	public static double getDouble (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		double result = 0;
		do{
			badInput = false;
			input = getString(ask);
			try {
				result = Double.parseDouble(input);
			}
			catch (NumberFormatException e) {
				badInput = true;
			}
		}while(badInput);
		return result;
	}
	
	/**
	 *	Returns double between min and max values, will keep asking 
	 * for double until user enters a value in between min and max
	 */
	public static double getDouble (String ask, double min, double max)
	{
		double doubleNum = 0.0;
		boolean numInRange = false;
		do{
			doubleNum = getDouble(ask);
			numInRange = (doubleNum >= min && doubleNum <= max);
		}while(!numInRange);
		return doubleNum;
	}
}