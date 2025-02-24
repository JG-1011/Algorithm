import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, maxDist, farthestNode;
    static boolean[] visited;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;
                int length = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Node(end, length));
            }
        }

        visited = new boolean[N + 1];
        maxDist = 0;
        farthestNode = 0;
        dfs(1, 0); // 지름이 되는 노드 찾는 dfs

        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(farthestNode, 0); // 지름 길이 구하기

        System.out.println(maxDist);
    }

    private static void dfs(int node, int sum) {
        visited[node] = true;

        if (sum > maxDist) {
            maxDist = sum;
            farthestNode = node;
        }

        for (int i = 0; i < graph.get(node).size(); i++) {
            Node next = graph.get(node).get(i);
            if (!visited[next.v]) {
                visited[next.v] = true;
                dfs(next.v, sum + next.w);

                visited[next.v] = false;
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