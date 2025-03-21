import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    static boolean[] isNetwork;
    static int answer;

    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        isNetwork = new boolean[n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!isNetwork[i]) {
                bfs(i);
                answer++;
            }
        }

        return answer;
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        isNetwork[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (!isNetwork[next]) {
                    isNetwork[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
