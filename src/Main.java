import java.util.Scanner;

public class Main 
{
	static passwordGenerator generator = new passwordGenerator();
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Password:	" + generator.generatePassword(scanner.next()));
		scanner.close();
	}
}