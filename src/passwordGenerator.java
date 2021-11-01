public class passwordGenerator 
{	
	// Class represents an interval for possible character ID #s
	private class ID_Range
	{
		final int LOWER_BOUND_ID;
		final int UPPER_BOUND_ID;
		
		ID_Range(int LOWER_BOUND_ID, int UPPER_BOUND_ID)
		{
			this.LOWER_BOUND_ID = LOWER_BOUND_ID;
			this.UPPER_BOUND_ID = UPPER_BOUND_ID;
		}
	};
	
	private final int NUM_OF_ID_INTERVALS = 6;
	
	// The range of possible character ID #s
	private final ID_Range ID_RANGE[] = 
	{
		new ID_Range(33, 33), new ID_Range(35, 38), new ID_Range(40, 57),
		new ID_Range(60, 90), new ID_Range(95, 95), new ID_Range(97, 122)
	}; 
	
	// Return the # of times a specific character occurs in a provided piece of text
	private int getCharOccurrences(String text, char character)
	{
		int num_of_occurrences = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == character)
			{
				num_of_occurrences++;
			}
		}
		
		return num_of_occurrences;
	}

	// Determine if an character ID # falls within the previously declared range
	private boolean ID_WithinRange(int char_ID)
	{	
		for(int i = 0; i < NUM_OF_ID_INTERVALS; i++)
		{	
			if(char_ID >= ID_RANGE[i].LOWER_BOUND_ID && char_ID <= ID_RANGE[i].UPPER_BOUND_ID)
			{
				return true;
			}
		}
		
		return false;
	}
	
	// Returns a password given an initial piece of text
	public String generatePassword(String initial_text)
	{
		int num_of_initial_chars = initial_text.length();
		String password = "";
		
		// current_char is the the index of a piece of text that's being looked at, from the 1st character, to the final
		// The for loop will repeat the segment just below until every character in initial_text has been looked at
		for(int current_char = 0; current_char < num_of_initial_chars; current_char++)
		{
			// When a given character from initial_text is looked at, the character ID is then displaced to create
			// new characters when it comes to generating the password. The displacement is calculated by dividing the # of
			// times the character in question occurs in the text by the total # of characters. That percentage is then
			// multiplied by the character ID of the current character in question, and if the number of occurrences from earlier
			// is positive, the displacement is positive, and it is negative, the displacement becomes negative. 
			
			// Assuming the derived character ID falls within the described range of possible IDs, the character is appended to the
			// password, otherwise the character that was derived in the previous iteration of the loop is appended instead. 
			double char_ID = initial_text.charAt(current_char);
			double char_occurrences = getCharOccurrences(initial_text, initial_text.charAt(current_char));
			int ID_displacement = (int)((char_occurrences / num_of_initial_chars) * char_ID);
			
			if(char_occurrences % 2 == 1)
			{
				ID_displacement = ID_displacement * -1;
			}
			
			if(ID_WithinRange((int)char_ID + ID_displacement))
			{
				password += (char)((int)char_ID + ID_displacement);
			}
			else if(password.length() > 0)
			{
				password += password.charAt(password.length() - 1);
			}
		}
		
		return password; 
	}
}
