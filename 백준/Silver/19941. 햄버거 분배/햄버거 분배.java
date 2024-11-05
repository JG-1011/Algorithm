import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> persons = new LinkedList<>();
        Queue<Integer> hamburgers = new LinkedList<>();

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'P') persons.add(i);
            else hamburgers.add(i);
        }

        int ans = 0;

        while (!persons.isEmpty() && !hamburgers.isEmpty()) {
            int p = persons.peek();
            int h = hamburgers.peek();

            if (Math.abs(p - h) <= K) {
                ans++;
                persons.poll();
                hamburgers.poll();
            } else if (p < h) {
                persons.poll();
            } else {
                hamburgers.poll();
            }
        }

        System.out.println(ans);
    }
}
