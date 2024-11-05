import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;

            for (int i = 0; i < 10; i++) {
                int num = Integer.parseInt(st.nextToken());
                sum += num;
            }

            long ans = Math.round(sum / 10.0);
            sb.append("#").append(test).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
