import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();
		//최솟값을 구하기 위해 처음 값을 주어질수 있는 문자열 최대 길이인 50을 저장.
		int result = 50;
		
		for(int i = 0; i <= (B.length() - A.length()); i++) {
			int count = 0;
			for(int j = 0; j < A.length(); j++) {
				if(A.charAt(j) != B.charAt(j + i)) {
					count++;
				}
			}
			result = Math.min(result, count);
		}
		System.out.println(result);
	}

}