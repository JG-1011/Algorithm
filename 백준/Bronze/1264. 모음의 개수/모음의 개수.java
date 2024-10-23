import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//무한루프
		while(true) {
			//입력받을 값을 대문자로 바꿔 저장.
			String S = br.readLine().toUpperCase();
			//모음의 개수를 저장 할 변수
			int count = 0;
			
			//#입력시 무한루프 종료
			if(S.equals("#")) break;
			
			for(int i = 0; i < S.length(); i++) {
				//모음은 5가지가 있으니 or로 각자 조건을 넣어준다.(길어서 3줄로 나눴다.)
				if(S.charAt(i) == 'A' || S.charAt(i) == 'E' 
						|| S.charAt(i) == 'I' || S.charAt(i) == 'O' 
						|| S.charAt(i) == 'U') count++;
			}
			System.out.println(count);
		}
	}

}