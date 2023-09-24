import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림 : 모든 정점들을 연결했을 때, 연결되는 가중치의 최소 합
// 데이크스트라 : 이것은 모든 정점이 연결안될 수도 있음 그냥 최단 거리를 구할 때 사용함
// 두개 잘 구분해서 사용하기

public class Main {

	static class Node {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	static List<List<Node>> graph;
	static boolean[] visit;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 수
		int M = Integer.parseInt(st.nextToken()); // 간선의 수

		graph = new ArrayList<>();
		visit = new boolean[N + 1];
		sum = 0;

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}
		//// 연결까지 끝

		bfs(new Node(1, 0));

		System.out.println(sum);

	}

	public static void bfs(Node start) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});

		queue.offer(start);

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (!visit[now.vertex]) {
				visit[now.vertex] = true;
				sum += now.weight;

				for (int i = 0; i < graph.get(now.vertex).size(); i++) {
					Node next = graph.get(now.vertex).get(i);
					queue.add(next);
				}
			}
		}
	}
}
