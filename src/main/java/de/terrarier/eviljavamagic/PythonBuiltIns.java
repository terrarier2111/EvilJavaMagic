import java.util.Scanner;

// This one isn't even that evil
public final class PythonBuiltIns {
  
  public static String input(String text, Scanner scanner) {
		System.out.print(text);
		return scanner.nextLine();
	}

	public static String input(String text) {
		try (Scanner scanner = new Scanner(System.in)) {
			return input(text, scanner);
		}
	}
  
}
