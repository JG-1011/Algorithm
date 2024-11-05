import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            String[] numbers = br.readLine().split(" ");
            int sum = 0;

            for (String numStr : numbers) {
                int num = Integer.parseInt(numStr);
                if (num % 2 != 0) {
                    sum += num;
                }
            }

            sb.append('#').append(test).append(' ').append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
