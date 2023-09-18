import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visit; // 방문확인을 위한 배열
	static int[] ans; // 순번 저장 배열
	static int cnt; // 몇번째로 방문을 했냐 체크해주기 위한 변수
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken()); // 정점
		int edge = Integer.parseInt(st.nextToken()); // 간선
		int startVertex = Integer.parseInt(st.nextToken()); // 시작정점

		ans = new int[vertex + 1]; // +1을 해준 이유는 그래프 숫자 1 2 3 4 5 와 인덱스를 맞춰주기 위해서
		visit = new boolean[vertex + 1]; // +1을 해준 이유는 그래프 숫자 1 2 3 4 5 와 인덱스를 맞춰주기 위해서

		for (int i = 0; i < vertex + 1; i++) {
			graph.add(new ArrayList<>());
		}

		// 간선 정보대로 연결해보자
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());

			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex); // 무방향이니까 반대로도 연결해줘야 한다.
		}

		// 문제에서 오름차순으로 뽑으라고 했기 때문에 정렬을 해준다.
		for (int i = 1; i < graph.size(); i++) {
			Collections.sort(graph.get(i));
		}

		// 시작정점도 순서에 포함 되므로 cnt =1;
		cnt = 1;

		// 깊탐색 드가자
		dfs(startVertex);

		for (int i = 1; i < ans.length; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int vertex) {
		visit[vertex] = true; // 들어오자마자 방문했다고 표시
		ans[vertex] = cnt++; // 현재 방문하고 있는 정점에 순서를 저장 / ans[1번정점] = 1 << 1번정점을 첫번째로 방문했다라는 뜻

		// 현재 위치한 정점이 갈 수 있는 정점 리스트를 순회
		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int next = graph.get(vertex).get(i); // 쓰기 힘드니까 변수명으로 빼줌
			if (!visit[next]) {
				dfs(next);
			}

		}
	}
}
