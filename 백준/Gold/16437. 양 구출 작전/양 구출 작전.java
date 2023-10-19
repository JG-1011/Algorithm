import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int v;
	long w; // 자기정점, 동물 수

	public Node(int v, long w) {
		this.v = v;
		this.w = w;
	}

}

public class Main {
	static int N;
	static List<List<Node>> graph;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 2; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			char t = st.nextToken().charAt(0); // 동물 종류
			long a = Integer.parseInt(st.nextToken()); // 수
			int p = Integer.parseInt(st.nextToken()); // 도착

			if (t == 'W')
				a *= (-1);

			graph.get(p).add(new Node(i, a));
		}
		sum = 0;
		// 시작을 어떻게 해야 함?

		System.out.println(dfs(new Node(1, 0)));
	}

	public static long dfs(Node now) {
		long sum = 0;
		// 깊이만큼 들어감

		// 자식노드들의 합
		for (int i = 0; i < graph.get(now.v).size(); i++) {
			sum += dfs(graph.get(now.v).get(i));
		}

		// 내 노드의 합을 더해서 부모에게 전달을 해준다
		if (sum + now.w < 0) {
			return 0;
		} else {
			return sum + now.w;
		}
	}
}
