import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int vertex, edge, cnt, max, startVertex;
	static List<List<Integer>> graph;
	static boolean[] visit;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			graph = new ArrayList<>();
			cnt = 0;

			st = new StringTokenizer(br.readLine());
			vertex = Integer.parseInt(st.nextToken()); // M
			edge = Integer.parseInt(st.nextToken()); // N

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
				visit = new boolean[vertex + 1];
				dfs(i, 0);
			}

			sb.append("#" + tc + " " + cnt).append("\n");

		} // tc
		System.out.println(sb);
	}

	public static void dfs(int vertex, int depth) {
		if (depth == 2) {
			for (int i = 0; i < graph.get(vertex).size(); i++) {
				if (graph.get(vertex).get(i) == startVertex) {
					cnt++;
				}
			}
			return;
		}

		// 이렇게 해도 되지만 파라미터 값에 넣어도 상관 없을 듯 ex) dfs(int vertex, int startVertex, int
		// depth)
		if (depth == 0) {
			startVertex = vertex;
		}

		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int next = graph.get(vertex).get(i);
			if (!visit[next] && vertex < next) { // vertex<next 이게 키 포인트.. hash로 중복을 삭제할 수 없다 왜냐하면 int[]은 참조값을 가지고 있으므로
				// 내부 비교가 불가하다(N과M 할 때 했던 방법)
				// 애초에  vertex < next 때문에 visit을 사용하지 않아도 됨
				visit[next] = true;
				dfs(next, depth + 1);
				visit[next] = false;
			}
		}

	}
}