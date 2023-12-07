import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
    	// 입력 받을 준비
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력할 준비
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 문자열을 지정한 구분자로 문자열을 쪼개주는 클래스
        // new StringTokenizer()사용시 디폴트로 공백과 엔터로 문자열을 쪼갠다.
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		                      //첫째줄에 받은 N K를 공백을 기준으로 쪼갠다.
		// nextToken()을 통해 쪼갠 문자열 N을 int N에 담는다.
		int N = Integer.parseInt(st.nextToken());
        // nextToken()을 통해 쪼갠 문자열 K을 int K에 담는다.
		int K = Integer.parseInt(st.nextToken());
		
		// 동전의 종류를 담은 인트 배열
		int[] coin = new int [N];
		
		for (int i = 0; i < N; i++) {
        	// 두번째 줄 이후의 동전의 종류들을 br.readLine()으로 받는다.
            // Integer.parseInt()를 통해 정수로 변환해 coin[i]에 담는다.
			coin[i] = Integer.parseInt(br.readLine());
		}
		// K원까지 필요한 동전의 개수를 세기 위한 변수
		int count = 0;
		for (int i = N-1; i >= 0; i--) {
			if(K >= coin[i]) {
				count += K / coin[i];
				K = K % coin[i];
			}
		}
		// bw.write()를 통해 출력한다. cf) System.out.print()보다 빠르다.
		// 정수인 count를 String.valueOf()로 문자열로 변환한다.
		bw.write(String.valueOf(count));
		
		// 사용한 br과 bw를 닫아준다.
		br.close();
		bw.close();
 	}
}