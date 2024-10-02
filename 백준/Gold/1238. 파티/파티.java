import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제
 * N명이 모여서
 * X번 마을에서 파티를 한다
 * M개의 단방향 도로가 있다.
 * i번째 길을 지나는데 T의 시간을 소비한다.
 * 파티에 참석했다가 다시 집으로 돌아와야 한다.
 * 최단시간으로 가고 싶음
 *
 * 가장 오래 걸리는 학생이 누구인지 구해라
 *
 * 입력
 * N,M,X가 공백으로 주어짐
 * 시작점, 끝점, 소요시간
 *
 * 다익스트라로 1번부터 N번까지 돌려보자
 *
 *
 *
 *
 *
 *
 *
 * */
public class Main {
    static int N, M, X, max;
    static int[] dist;
    static List<List<Node>> graph;

    static final int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, time));
        }

        dist = new int[N + 1];

        max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            int distance = dijkstra(i, X) + dijkstra(X, i);

            max = Math.max(max, distance);
        }

        System.out.println(max);

    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < graph.get(now.v).size(); i++) {
                Node next = graph.get(now.v).get(i);
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;

                    pq.add(new Node(next.v, dist[next.v]));
                }

            }

        }

        return dist[end];
    }
}

class Node {
    int v, w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}