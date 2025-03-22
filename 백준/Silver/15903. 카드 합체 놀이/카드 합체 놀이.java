import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * n장 가지고 있고
 * 1. x,y카드를 골라 합
 * 2. 계산한 값을 x,y 값에 넣는다
 * m번 진행 후 모든 카드를 더한다.
 * 가장 작은 점수를 구해라
 *
 * 3 2 6
 * 작은걸 두개 골라서
 * 2 + 3 = 5
 * 5 5 6
 *
 * 4 2 3 1
 * 2 + 1 = 3
 * 4 3 3 3
 * 3 + 3 = 6
 * 4 6 6 3
 *
 * x와 y를 뽑는 도구 > pq
 * 두개 꺼내고 더하고 다시 넣어
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            long x = pq.poll();
            long y = pq.poll();
            long sum = x + y;

            pq.add(sum);
            pq.add(sum);
        }

        long ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}