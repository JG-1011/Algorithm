import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] ans, arr;
	static boolean[] visit;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int startVertex = Integer.parseInt(st.nextToken());

		visit = new boolean[vertex + 1];
		ans = new int[vertex + 1];

		// graph 리스트 안에 정점+1(인덱스와 숫자 맞추려고) 만큼 어레이리스트를 만들어준다.
		for (int i = 0; i < vertex + 1; i++) {
			graph.add(new ArrayList<>());
		}

		// 간선 정보를 받고 무방향으로 값을 넣어준다.
		// 0부터 시작 : 간선 개수대로만 넣어주면 되므로
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());

			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}

		// 정점에 넣어진 값들을 오름차순으로 정렬해준다.
		// 인덱스 1번부터 넣어줬으므로 1부터 시작
		for (int i = 1; i < vertex + 1; i++) {
			Collections.sort(graph.get(i));
		}
		cnt = 1;
		bfs(startVertex);

		for (int i = 1; i < ans.length; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.println(sb);
	}

	public static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(vertex);
		visit[vertex] = true;
		ans[vertex] = cnt++;

		while (!queue.isEmpty()) {
			int num = queue.poll();

			for (int i = 0; i < graph.get(num).size(); i++) {
				int next = graph.get(num).get(i);
				if (!visit[next]) {
					queue.offer(next);
					visit[next] = true;
					ans[next] = cnt++;
				}
			}
		}
	}
}
