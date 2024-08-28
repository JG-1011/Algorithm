import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 올림픽과 비슷한 문제
 *
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        int[] rankArr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int rank = 1;
            int targetW = arr[i][0];
            int targetH = arr[i][1];
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (targetW < arr[j][0] && targetH < arr[j][1]) {
                    rank++;
                }
            }
            rankArr[i] = rank;
        }

        for (int x : rankArr) {
            sb.append(x).append(" ");
        }
        
        System.out.println(sb);

    }
}
