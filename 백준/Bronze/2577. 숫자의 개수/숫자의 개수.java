import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		int N = A * B * C;
		String S = String.valueOf(N);
		
		for(int i = 0; i <= 9; i++) {
			int count = 0;
			for(int j = 0; j < S.length(); j++) {
				if((S.charAt(j) - '0') == i) count++;
			}
			System.out.println(count);
		}
	}

}