import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		double ans = Math.pow(0.5, n);
		
		// BigDecimal은 고정 소수점 연산을 해주며 십진 표현을 제공
        BigDecimal bd = new BigDecimal(ans);
        
        // n자리 수까지 소수점을 표기, RoundingMode.HALF_UP 반올림
        bd = bd.setScale(n, RoundingMode.HALF_UP);

		bw.write(bd.toPlainString());
		bw.flush();
		bw.close();
		br.close();
	}
}