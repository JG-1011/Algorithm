import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//나는 숫자를 뽑기만 하면 되는거 아닌가??

public class Main {
	static int N, K, cnt;
	static int[] visit;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visit = new int[100000+1];
		cnt = 0;

		if (N == K) {
			System.out.println(0);
		} else {
			bfs(N);
			System.out.println(visit[K]-1); // 시작점 값이 0이 아니라 1로 시작해서 -1를 해줘야 한다.
		}
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(start);
		visit[start] = 1;

		while (!queue.isEmpty()) {
			int num = queue.poll();
			if (num == K) {
				return;
			}

			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0) {
					next = num + 1;
				} else if (i == 1) {
					next = num - 1;
				} else {
					next = num * 2;
				}
				if (next >= 0 && next < visit.length && visit[next] == 0) {
					queue.add(next);
					visit[next] = visit[num] + 1;
				}
			}
		}
	}
}
