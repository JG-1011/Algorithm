import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] ans;
	static boolean[] visit;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken()); // 정점
		int edge = Integer.parseInt(st.nextToken()); // 간선
		int startVertex = Integer.parseInt(st.nextToken()); // 시작정점

		ans = new int[vertex + 1];
		visit = new boolean[vertex + 1];

		for (int i = 0; i < ans.length; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());

			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}

		for (int i = 0; i < graph.size(); i++) {
			Collections.sort(graph.get(i), (o1, o2) -> {
				return o2 - o1;
			});
		}

		cnt = 1;
		dfs(startVertex);

		for (int i = 1; i < ans.length; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int vertex) {
		visit[vertex] = true;
		ans[vertex] = cnt++;

		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int next = graph.get(vertex).get(i);
			if (!visit[next]) {
				dfs(next);
			}
		}

	}
}
