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
		int vertex; // 도착 정점
		int weight; // 가중치

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

	}

	static boolean[] visit;
	static int[] dist;
	static final int INF = 200000000;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 정점의 수
		int M = Integer.parseInt(br.readLine()); // 간선의 수

		visit = new boolean[N + 1];
		dist = new int[N + 1];

		graph = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발 정점
			int v = Integer.parseInt(st.nextToken()); // 도착 정점
			int w = Integer.parseInt(st.nextToken()); // 간선의 가중치

			graph.get(u).add(new Node(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		bfs(new Node(start, 0));
		
		System.out.println(dist[end]);

	}

	public static void bfs(Node start) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});

		queue.add(start);
		dist[start.vertex] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (!visit[now.vertex]) {
				visit[now.vertex] = true;  // 최소 거리 이후 갱신위해서 꺼낼때 방문처리
				for (Node next : graph.get(now.vertex)) {
					if(dist[next.vertex] > dist[now.vertex] + next.weight) {
						dist[next.vertex] = dist[now.vertex] + next.weight;
						queue.add(new Node(next.vertex, dist[next.vertex]));
					}
				}
			}
		}
	}
}
