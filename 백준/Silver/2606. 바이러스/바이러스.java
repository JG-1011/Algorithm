import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visit; // 방문체크배열
	static int cnt; // 바이러스에 걸리는 컴퓨터를 카운트하기 위한 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 정점과 간선 수 입력 받기
		int vertex = Integer.parseInt(br.readLine());
		int edge = Integer.parseInt(br.readLine());

		// 인덱스와 정점 숫자를 맞추기 위해 +1 해서 만든다
		visit = new boolean[vertex + 1];

		// 리스트안에 리스트 만들기
		for (int i = 0; i < vertex + 1; i++) {
			graph.add(new ArrayList<>());
		}

		// 간선 모두 연결
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());

			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		////////////// 연결완료
		cnt = 0;

		bfs(1);

		System.out.println(cnt - 1); // 처음 들어가는 1은 빼야하니까 -1
	}

	// bfs 공식
	public static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(vertex);
		visit[vertex] = true;
		cnt++;

		while (!queue.isEmpty()) {
			int num = queue.poll();

			for (int i = 0; i < graph.get(num).size(); i++) {
				int next = graph.get(num).get(i);
				if (!visit[next]) {
					queue.offer(next);
					visit[next] = true;
					cnt++;
				}
			}
		}
	}

}