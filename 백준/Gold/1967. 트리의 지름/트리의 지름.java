import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제점
 * 노드의 끝을 어떻게 알고 지름 값을 구하지?
 *
 * */
public class Main {
    static int maxDist;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, length));
            graph.get(end).add(new Node(start, length));
        }

        maxDist = 0;
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            dfs(i, visited, 0);
        }

        System.out.println(maxDist);
    }

    private static void dfs(int start, boolean[] visited, int sum) {
        visited[start] = true;

        if (sum > maxDist) {
            maxDist = sum;
        }

        for (int i = 0; i < graph.get(start).size(); i++) {
            Node next = graph.get(start).get(i);
            if (!visited[next.v]) {
                dfs(next.v, visited, sum + next.w);
            }
        }
    }
}

class Node {
    int v, w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}