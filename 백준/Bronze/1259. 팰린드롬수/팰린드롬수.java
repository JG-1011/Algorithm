import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//무한루프
		while(true) {
			String S = br.readLine();
			//StringBuilder를 선언하면서 입력값인 S를 저장한다.
			StringBuilder sb = new StringBuilder(S);
			//StringBuilder 내장 함수 reverse를 사용하지만 원래는 같은 StringBuilder에 저장가능하기에
			//String으로 변환해서 저장한다. 그것이 뒤에 .toString()이다.
			String Sreverse = sb.reverse().toString();
			
			//0 입력시 무한루프 종료
			if(S.equals("0")) break;
			
			//두 문자열을 비교
			if(S.equals(Sreverse)) {
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
	}

}