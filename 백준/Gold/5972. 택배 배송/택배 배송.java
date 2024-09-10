import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 해결방법
 * 다익스트라 사용
 *
 * */
public class Main {
    static List<List<Node>> graph;
    static int[] dist;
    static final int INF = 200000000;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수

        dist = new int[N + 1];
        visit = new boolean[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        bfs(new Node(1, 0));

        System.out.println(dist[N]);
    }

    private static void bfs(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(start);
        dist[start.vertex] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visit[now.vertex]) {
                visit[now.vertex] = true;

                for (Node next : graph.get(now.vertex)) {
                    if (dist[next.vertex] > dist[now.vertex] + next.weight) {
                        dist[next.vertex] = dist[now.vertex] + next.weight;
                        pq.add(new Node(next.vertex, dist[next.vertex]));
                    }
                }
            }
        }
    }
}

class Node {
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}