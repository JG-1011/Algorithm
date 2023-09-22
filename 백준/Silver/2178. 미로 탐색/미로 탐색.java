import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N, M;
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 방향 벡터
    static int[] dc = { 0, 0, -1, 1 };
    static int[][] map, map2; // 초기 미로와 최단 거리를 저장할 배열
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 미로의 행 개수
        M = Integer.parseInt(st.nextToken()); // 미로의 열 개수

        map = new int[N][M]; // 초기 미로
        map2 = new int[N][M]; // 최단 거리를 저장할 배열
        visit = new boolean[N][M]; // 방문 여부를 나타내는 배열

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0'; // 미로 정보를 배열에 저장
            }
        }

        bfs(0, 0); // BFS를 시작점(0, 0)에서 호출

        System.out.println(map2[N - 1][M - 1]); // 목적지까지의 최단 거리 출력
    }

    public static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c)); // 시작점을 큐에 추가
        visit[r][c] = true;
        map2[r][c] = 1; // 시작점에서의 최단 거리를 1로 초기화

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) { // 상하좌우로 이동
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] == 0) {
                    // 범위를 벗어나거나 이미 방문한 곳이거나 이동할 수 없는 칸인 경우 무시
                    continue;
                }
                queue.offer(new Point(nr, nc)); // 새로운 위치를 큐에 추가
                visit[nr][nc] = true; // 방문 표시
                map2[nr][nc] = map2[p.r][p.c] + 1; // 최단 거리 갱신
            }
        }
    }
    
//	public static void bfs(int r, int c) {
//    Queue<Point> queue = new LinkedList<>(); // BFS를 위한 큐 생성
//    queue.offer(new Point(r, c)); // 시작점을 큐에 넣음
//    visit[r][c] = true; // 시작점을 방문 표시하고 이동한 칸 수(cnt)를 1로 초기화
//
//    while (!queue.isEmpty()) { // 큐가 비어있지 않은 동안 반복
//        int size = queue.size(); // 현재 레벨의 모든 노드를 방문하기 위해 큐의 크기를 저장
//
//        cnt++; // 이동한 칸 수를 1 증가시킴 (현재 레벨에서 이동한 칸 수)
//
//        for (int i = 0; i < size; i++) { // 현재 레벨의 모든 노드를 처리
//            Point p = queue.poll(); // 큐에서 노드를 하나 꺼냄
//            if (p.r == N - 1 && p.c == M - 1) { // 목적지에 도달한 경우 종료
//                return;
//            }
//
//            for (int d = 0; d < 4; d++) { // 상하좌우로 이동
//                int nr = p.r + dr[d]; // 새로운 행 위치 계산
//                int nc = p.c + dc[d]; // 새로운 열 위치 계산
//                if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visit[nr][nc] && map[nr][nc] == 1) {
//                    // 새로운 위치가 미로 범위 내이고, 방문하지 않았으며, 이동 가능한 칸인 경우
//                    queue.offer(new Point(nr, nc)); // 큐에 새로운 위치를 넣음
//                    visit[nr][nc] = true; // 새로운 위치를 방문 표시함
//                }
//            }
//        }
//    }
//}
}