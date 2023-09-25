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
	static int N, M, R; // 정점의 수, 간선의 수, 시작 정점
	static List<ArrayList<Integer>> list; // 2차원 리스트 생성
	static boolean[] visited;
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 수
		M = Integer.parseInt(st.nextToken()); // 간선의 수
		R = Integer.parseInt(st.nextToken()); // 시작 정점

		res = new int[N + 1];

		list = new ArrayList<>();

		// 리스트 넣어주기
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}

		// 간선의 값 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			// 양방향 간선
			list.get(u).add(v);
			list.get(v).add(u);
		}

		// 오름차순으로 방문 -> 정렬 필요
		for (int i = 1; i < N + 1; i++) {
			Collections.sort(list.get(i));
		}

		bfs();

		for (int i = 1; i < N + 1; i++) {
			System.out.println(res[i]);
		}

	}// main

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>(); // 큐 생성
		visited = new boolean[N + 1]; // 방문 체크 배열
		int cnt = 1; // 방문 순서는 1부터 시작

		q.add(R); // 시작 정점을 큐에 먼저 넣어주기
		visited[R] = true; // 방문했음을 체크
		res[R] = cnt++; // R번 노드의 방문 순서를 넣고 값 증가 시키기

		// 큐가 빌 때 까지 while문 반복하기
		while (!q.isEmpty()) {
			int tmp = q.poll();

			for (int i = 0; i < list.get(tmp).size(); i++) {
				int next = list.get(tmp).get(i);

				if (visited[next] == false) {
					visited[next] = true;
					q.add(next);
					res[next] = cnt++;
				}
			}

		} // while

	}// bfs

}