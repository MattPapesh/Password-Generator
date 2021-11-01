import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main 
{
	static passwordGenerator generator = new passwordGenerator();
	
	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Password:	" + generator.generatePassword(scanner.next()));
			scanner.close();
		}
		catch(NoSuchElementException e) {}
		catch(IllegalStateException e) {}
	}
}