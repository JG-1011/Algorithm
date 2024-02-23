import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        // BufferedReader와 BufferedWriter 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스의 개수(N) 입력 받기
        int N = Integer.parseInt(br.readLine());

        // 각 테스트 케이스 처리
        for (int i = 1; i <= N; i++) {
            // StringTokenizer를 사용하여 공백을 기준으로 A와 B를 분리
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // A 값
            int b = Integer.parseInt(st.nextToken()); // B 값

            // "Case #x: A+B" 형식으로 출력
            bw.write("Case #" + i + ": " + (a + b) + "\n");
        }

        // 입력 스트림과 출력 스트림 닫기
        br.close();
        bw.flush();
        bw.close();
    }
}