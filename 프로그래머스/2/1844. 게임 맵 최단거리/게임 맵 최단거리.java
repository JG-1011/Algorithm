import java.util.*;
/*
(1,1)에서 시작
사방탐색
bfs이용
1 : 이동가능
0 : 벽
*/
class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static class Point {
        int r,c,cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        
        visited[0][0] = true;
        queue.add(new Point(0, 0, 1));
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            
            if (now.r == N - 1 && now.c == M - 1) return now.cnt;
            
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc] || maps[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, now.cnt + 1));
            }
        }
        
        return -1;
    }
}