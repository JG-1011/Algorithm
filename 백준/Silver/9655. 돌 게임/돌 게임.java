import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
조건
1. 이기는 조건 : 마지막 돌을 가져가는 사람
2. 1개 or 3개를 가져갈 수 있음
3. 상근 먼저 시작

풀이
1. 돌을 가져가는 상황 : 1개vs1개, 1개vs3개, 3개vs3개
2. 최소로 가져가는 돌의 수 2개로 나눴을 때
3. 나머지가 없으면 창영 승, 나머지가 있으면 상근 승
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        if (N % 2 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }

}