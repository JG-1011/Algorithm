import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int vertex, weight; // 도착정점, 가중치

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

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

		int startVertex = Integer.parseInt(br.readLine());

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
		}

		bfs(new Node(startVertex, 0));

		for (int i = 1; i < N + 1; i++) {
			if (dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}

	public static void bfs(Node start) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});

		queue.offer(start);
		dist[start.vertex] = 0; // 시작정점에서 시작정점(도착지점)까지 거리 0

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (!visit[now.vertex]) {
				visit[now.vertex] = true;

				for (int i = 0; i < graph.get(now.vertex).size(); i++) {
					Node next = graph.get(now.vertex).get(i);
					if (dist[next.vertex] > dist[now.vertex] + next.weight) {
						dist[next.vertex] = dist[now.vertex] + next.weight;
						queue.add(new Node(next.vertex, dist[next.vertex]));
					}
				}
			}
		}
	}
}
