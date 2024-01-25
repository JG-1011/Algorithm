import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int sum_value = 0;
		for (int i=1; i<=n; i++) {
			sum_value += i;
		}
		System.out.println(sum_value);
	}
}