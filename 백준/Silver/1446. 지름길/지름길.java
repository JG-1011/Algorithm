import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Road> roads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            if (e <= D) {
                roads.add(new Road(s, e, l));
            }
        }

        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i <= D; i++) {
            if (i > 0) {
                dist[i] = Math.min(dist[i], dist[i - 1] + 1);
            }

            for (Road road : roads) {
                if (i == road.start) {
                    dist[road.end] = Math.min(dist[road.start] + road.length, dist[road.end]);
                }
            }
        }

        System.out.println(dist[D]);

    }
}

class Road {
    int start, end, length;

    public Road(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}