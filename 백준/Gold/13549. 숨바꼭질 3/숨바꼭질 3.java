import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 해결방법
 * BFS로 풀이해보자, 순간이동 할 때 시간추가 x, 걷기 할 때 시간추가 o
 *
 * 문제점
 * 처음에는 visited를 boolean형으로 처리를 했었는데 GPT에게 물어보니
 * time을 넣어서 d==0이나 d==1일 때 시간을 증가 시켰는데, 이 방식은 모든 노드에 대한 시간 변수를 직접 관리하기 때문에
 * BFS의 특성을 제대로 활용하지 못한 것이래..
 * BFS는 이미 최단 경로를 보장하는 알고리즘으로 time++을 이렇게 관리하면 최단거리를 계산하기 어렵대
 *
 * */
public class Main {
    static class Point {
        int c, time;

        public Point(int c, int time) {
            this.c = c;
            this.time = time;
        }
    }

    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[100001];

        min = 1000000;
        BFS(N, K);
        System.out.println(min);
    }

    private static void BFS(int subin, int sister) {
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        queue.add(new Point(subin, 0));
        visited[subin] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.c == sister) min = Math.min(min, now.time);

            int[] nextPositions = {now.c * 2, now.c - 1, now.c + 1};

            for (int d = 0; d < 3; d++) {
                int next = nextPositions[d];
                if (inRange(next) && !visited[next]) {
                    queue.add(new Point(next, now.time + (d == 0 ? 0 : 1)));
                    visited[next] = true;
                }
            }
        }
    }


    private static boolean inRange(int nc) {
        return nc >= 0 && nc < 100001;

    }
}