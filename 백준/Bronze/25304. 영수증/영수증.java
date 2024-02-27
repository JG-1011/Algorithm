import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int sum = 0;
        int price = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            sum += num * cnt;
        }

        if (sum == price) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
