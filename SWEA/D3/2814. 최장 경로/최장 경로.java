import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int vertex, edge, cnt, max;
	static List<List<Integer>> graph;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// map에 대한 입력값 넣기
			st = new StringTokenizer(br.readLine());
			vertex = Integer.parseInt(st.nextToken()); // N
			edge = Integer.parseInt(st.nextToken()); // M

			graph = new ArrayList<>();
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
			max = 1;
			for (int i = 1; i < vertex + 1; i++) {
				visit = new boolean[vertex + 1];
				dfs(i, 1);
			}
			sb.append("#" + tc + " " + max).append("\n");
		} // tc
		System.out.println(sb);
	}
	// bfs로 풀기 어렵다..
	public static void dfs(int vertex, int cnt) {
		visit[vertex] = true;
		if (max < cnt) {
			max = cnt;
		}

		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int next = graph.get(vertex).get(i);
			if (!visit[next]) {
				dfs(next, cnt + 1);
				visit[next] = false;
			}

		}

	}
}