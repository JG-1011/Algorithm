import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited; // [x][y][벽 부숨 여부]
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, distance, breakChance;

        public Node(int x, int y, int distance, int breakChance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.breakChance = breakChance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // [벽을 부수지 않음(0), 부숨(1)]

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0)); // 시작점 (벽을 아직 부수지 않음)
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 도착점에 도달하면 거리 반환
            if (now.x == N - 1 && now.y == M - 1) {
                return now.distance;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                // 범위를 벗어나면 패스
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 1. 벽이 아닌 경우, 그냥 이동 가능
                if (map[nx][ny] == 0 && !visited[nx][ny][now.breakChance]) {
                    visited[nx][ny][now.breakChance] = true;
                    queue.add(new Node(nx, ny, now.distance + 1, now.breakChance));
                }

                // 2. 벽인 경우, 부술 수 있는 기회가 있다면 부수고 이동
                if (map[nx][ny] == 1 && now.breakChance == 0) {
                    visited[nx][ny][1] = true;
                    queue.add(new Node(nx, ny, now.distance + 1, 1)); // 벽을 부순 상태로 이동
                }
            }
        }

        return -1; // 도착 불가능한 경우
    }
}
