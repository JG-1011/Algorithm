import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static List<List<Integer>> graph;
	static int cnt;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// 정점과 간선 수 입력 받기
			st = new StringTokenizer(br.readLine());
			int vertex = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			visit = new boolean[vertex + 1];
			cnt = 0;

			for (int i = 0; i < vertex + 1; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < edge; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());

				graph.get(fromVertex).add(toVertex);
				graph.get(toVertex).add(fromVertex);
			}

			for (int i = 1; i < vertex + 1; i++) {
				if (!visit[i]) {
					bfs(i);
					cnt++;
				}

			}

			System.out.println("#"+tc+" "+cnt);
		}
	}

	public static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(vertex);
		visit[vertex] = true;

		while (!queue.isEmpty()) {
			int num = queue.poll();

			for (int i = 0; i < graph.get(num).size(); i++) {
				int next = graph.get(num).get(i);
				if (!visit[next]) {
					queue.offer(next);
					visit[next] = true;
				}
			}
		}

	}
}