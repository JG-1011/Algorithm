import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner str = new Scanner(System.in);
		
		String s = str.nextLine();
		str.close();
		
		StringTokenizer st = new StringTokenizer(s, " ");
		
		System.out.println(st.countTokens());
	}
}