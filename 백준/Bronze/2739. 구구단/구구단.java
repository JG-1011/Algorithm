import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());	
		br.close();
		
		StringBuilder sb = new StringBuilder();
        
		for(int i = 1; i<10;i++) {
			sb.append(a).append(' ').append('*').append(' ').append(i);
			sb.append(' ').append('=').append(' ').append(a*i).append('\n');
		}
		System.out.print(sb);
	}
}