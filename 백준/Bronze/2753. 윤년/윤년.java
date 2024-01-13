import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//4의 배수인 것과 100의 배수가 아닌 것은 두 조건이 다 충족되어야 윤년이다.
		//400의 배수인 것은 앞서 다른 조건들이 충족되지 않아도 400의 배수면 윤년이다.
		if(N % 4 == 0 && N % 100 != 0 || N % 400 == 0) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}

}