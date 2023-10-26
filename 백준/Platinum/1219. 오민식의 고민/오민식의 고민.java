import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge {
	int start, end, weigt;

	public Edge(int start, int end, int weigt) {
		this.start = start;
		this.end = end;
		this.weigt = weigt;
	}

}

public class Main {
	static int N, from, to, M;
	static List<Edge> graph;
	static long[] dist;
	static int[] cost;
	static final int INF = Integer.MIN_VALUE;
	static boolean[][] linkGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		cost = new int[N];
		dist = new long[N];
		linkGraph = new boolean[N][N];
		Arrays.fill(dist, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			linkGraph[a][b] = true;
			graph.add(new Edge(a, b, c));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		if (bell(from)) {
			System.out.println("Gee");
		} else {
			System.out.println(dist[to]);
		}

	}

	private static boolean bell(int start) {
		dist[start] = cost[start];

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				Edge now = graph.get(j);

				if (dist[now.start] != INF) {
					if (dist[now.end] < dist[now.start] - now.weigt + cost[now.end]) {
						dist[now.end] = dist[now.start] - now.weigt + cost[now.end];

					}
				}
			}
		}

		if (dist[to] == INF) {
			System.out.println("gg");
			System.exit(0);
		}
		
		// 사이클이 존재한다면
		for (int i = 0; i < M; i++) {
			Edge now = graph.get(i);

			if (dist[now.start] != INF) {
				if (dist[now.end] < dist[now.start] - now.weigt + cost[now.end]) {
					if (bfs(now.end)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean bfs(int start) {

		boolean[] visit = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();

		queue.add(start);
		visit[start] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == to) {
				return true;
			}

			for (int i = 0; i < N; i++) {
				if (linkGraph[now][i] && !visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
		}
		return false;
	}
}
