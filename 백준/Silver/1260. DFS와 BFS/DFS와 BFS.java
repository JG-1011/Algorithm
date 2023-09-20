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
	static boolean[] dVisit, bVisit; // 방문체크배열
	static int[] dResult, bResult;
	static int dCnt, bCnt; // 바이러스에 걸리는 컴퓨터를 카운트하기 위한 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 정점과 간선 수 입력 받기
		st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int startVertex = Integer.parseInt(st.nextToken());

		dResult = new int[vertex + 1];
		bResult = new int[vertex + 1];
		dVisit = new boolean[vertex + 1];
		bVisit = new boolean[vertex + 1];

		// 리스트 안에 어레이리스트 넣기
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
			Collections.sort(graph.get(i));
		}

		dCnt = 1;
		bCnt = 1;

		bfs(startVertex);
		dfs(startVertex);

		for (int i = 1; i < dResult.length; i++) {
			if (dResult[i] != 0)
				sb.append(dResult[i] + " ");
		}

		sb.append("\n");

		for (int i = 1; i < bResult.length; i++) {
			if (bResult[i] != 0)
				sb.append(bResult[i] + " ");
		}

		System.out.println(sb);

	}

	public static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(vertex);
		bVisit[vertex] = true;
		bResult[bCnt++] = vertex;

		while (!queue.isEmpty()) {
			int num = queue.poll();

			for (int i = 0; i < graph.get(num).size(); i++) {
				int next = graph.get(num).get(i);
				if (!bVisit[next]) {
					queue.offer(next);
					bVisit[next] = true;
					bResult[bCnt++] = next;
				}
			}

		}

	}

	public static void dfs(int vertex) {
		dVisit[vertex] = true;
		dResult[dCnt++] = vertex;

		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int next = graph.get(vertex).get(i);
			if (!dVisit[next]) {
				dfs(next);
			}
		}

	}

}