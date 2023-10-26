import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int start, end, weight;

	public Node(int from, int to, int weight) {
		this.start = from;
		this.end = to;
		this.weight = weight;
	}

}

public class Main {
	static int N, M;
	static final int INF = 1234567890;

	static long[] dist;
	static boolean[] visit;
	static List<Node> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// dist 배열 초기화
		dist = new long[N + 1];
		Arrays.fill(dist, INF);

		visit = new boolean[N + 1];

		graph = new ArrayList<>();

		// 간선 정보 입력으로 받는다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Node(a, b, c));
		}

		// 출력
		if (bell(1)) {
			sb.append(-1 + " ").append("\n");
		} else {
			for (int i = 2; i < N + 1; i++) {
				sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
			}
		}

		System.out.println(sb);
	}

	private static boolean bell(int start) {
		dist[start] = 0;

		// 정점 -1 개수만큼 반복 (마지막은 사이클도는거니까 빼)
		for (int i = 0; i < N - 1; i++) {
			// 간선 개수만큼 반복
			for (int j = 0; j < M; j++) {
				Node now = graph.get(j); // 현재 간선

				if (dist[now.start] != INF) {
					if (dist[now.end] > dist[now.start] + now.weight) {
						dist[now.end] = dist[now.start] + now.weight;
					}
				}
			}
		}

		// 음의 가중치 확인
		for (int i = 0; i < M; i++) {
			Node node = graph.get(i);

			if (dist[node.start] != INF) {
				if (dist[node.end] > dist[node.start] + node.weight) {
					return true;
				}
			}
		}
		return false;
	}
}
