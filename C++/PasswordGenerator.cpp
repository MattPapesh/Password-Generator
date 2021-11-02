#include <iostream>
#include <string>
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>

class PasswordGenerator
{
    // struct represents an interval for possible character ID #s
    struct ID_Range
	{
		const int LOWER_BOUND_ID;
		const int UPPER_BOUND_ID;

		ID_Range(int LOWER_BOUND_ID, int UPPER_BOUND_ID)
		:   LOWER_BOUND_ID(LOWER_BOUND_ID), UPPER_BOUND_ID(UPPER_BOUND_ID)
		{}
	};

	const int NUM_OF_ID_INTERVALS = 6;

	// The range of possible character ID #s
	const ID_Range ID_RANGE[6] =
	{
		ID_Range(33, 33), ID_Range(35, 38), ID_Range(40, 57),
		ID_Range(60, 90), ID_Range(95, 95), ID_Range(97, 122)
	};

	// Return the # of times a specific character occurs in a provided piece of text
	int getCharOccurrences(std::string text, char character)
	{
		int num_of_occurrences = 0;

		for(int i = 0; i < text.size(); i++)
		{
			if(text[i] == character)
			{
				num_of_occurrences++;
			}
		}

		return num_of_occurrences;
	}

	// Determine if an character ID # falls within the previously declared range
	bool ID_WithinRange(int char_ID)
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

	public:
	// Returns a password given an initial piece of text
	std::string generatePassword(std::string text)
	{
		std::string password = "";
		int password_length = 0;

		// current_char is the the index of a piece of text that's being looked at, from the 1st character, to the final
		// The for loop will repeat the segment just below until every character in initial_text has been looked at
		for(int current_char = 0; current_char < text.size(); current_char++)
		{
			// When a given character from initial_text is looked at, the character ID is then displaced to create
			// new characters when it comes to generating the password. The displacement is calculated by dividing the # of
			// times the character in question occurs in the text by the total # of characters. That percentage is then
			// multiplied by the character ID of the current character in question, and if the number of occurrences from earlier
			// is positive, the displacement is positive, and it is negative, the displacement becomes negative.

			// Assuming the derived character ID falls within the described range of possible IDs, the character is appended to the
			// password, otherwise the character that was derived in the previous iteration of the loop is appended instead.
			double char_ID = text[current_char];
			double char_occurrences = getCharOccurrences(text, text[current_char]);
			int ID_displacement = (int)((char_occurrences / text.size()) * char_ID);

			if((int)char_occurrences % 2 == 1)
			{
				ID_displacement = ID_displacement * -1;
			}

			if(ID_WithinRange((int)char_ID + ID_displacement))
			{
				password += (char)((int)char_ID + ID_displacement);
			}
			else if(password_length > 0)
			{
				password += password[password_length - 1];
			}
		}

		return password;
	}
};

int main()
{
    // Instantiate an object of the PasswordGenerator class
    PasswordGenerator generator = PasswordGenerator();
    std::string input = "";

    // Continues to loop indefinitely; program can only end if the window is manually closed
    // Take in a provided text, create a password, and then display the password; rest the process when any key is pressed.
    while(true)
    {
        printf("Hello! This is the Password Generator! \n");
        printf("Provide any sample of text, whether a name, title, etc, and a password will be derived from it. (no spaces)\n\n");

        printf("Input: ");
        std::cin >> input;
        printf("Password: %s \n \npress any key to reset...", generator.generatePassword(input).c_str());

        while(true)
        {
            if(getch())
            {
                system("cls");
                break;
            }
        }
    }

    return 0;
}
