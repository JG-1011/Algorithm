import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static List<List<Point>> spaceList = new ArrayList<>();
    static List<List<Point>> puzzleList = new ArrayList<>();
    
    public int solution(int[][] game_board, int[][] table) {
        int N = game_board.length;
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && game_board[i][j] == 0) {
                    List<Point> tempList = new ArrayList<>();
                    dfs(tempList, game_board, visited, i, j, N, 0);
                    spaceList.add(normalize(tempList));
                }
            }
        }
        
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && table[i][j] == 1) {
                    List<Point> tempList = new ArrayList<>();
                    dfs(tempList, table, visited, i, j, N, 1);
                    puzzleList.add(normalize(tempList));
                }
            }
        }

        int answer = 0;
        boolean[] used = new boolean[puzzleList.size()];
        for (List<Point> space : spaceList) {
            for (int i = 0; i < puzzleList.size(); i++) {
                if (used[i]) continue;
                
                List<Point> puzzle = puzzleList.get(i);
                
                if (space.size() != puzzle.size()) continue;
                
                boolean match = false;
                for (int d = 0; d < 4; d++) {
                    if (isSame(space, puzzle)) {
                        used[i] = true;
                        answer += space.size();
                        match = true;
                        break;
                    }
                    
                    puzzle = rotate(puzzle);
                }
                
                if (match) break;
            }
        }
        
        return answer;
    }
    
    public static void dfs(List<Point> tempList, int[][] map, boolean[][] visited, int r, int c, int N, int target) {
        visited[r][c] = true;
        tempList.add(new Point(r, c));
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (visited[nr][nc] || map[nr][nc] != target) continue;
            
            dfs(tempList, map, visited, nr, nc, N, target);
        }
    }
    
    public static List<Point> normalize(List<Point> tempList) {
        List<Point> result = new ArrayList<>();
        
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for (Point p : tempList) {
            minR = Math.min(minR, p.r);
            minC = Math.min(minC, p.c);
        }
        
        for (Point p : tempList) {
            result.add(new Point(p.r - minR, p.c - minC));
        }
        
        result.sort((a, b) -> {
            if (a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
        
        return result;
    }
    
    public static boolean isSame(List<Point> space, List<Point> puzzle) {
        
        for (int i = 0; i < space.size(); i++) {
            if(space.get(i).r != puzzle.get(i).r || space.get(i).c != puzzle.get(i).c) return false;
        }
        
        return true;
    }
    
    public static List<Point> rotate(List<Point> puzzle) {
        List<Point> result = new ArrayList<>();
        
        for (Point p : puzzle) {
            result.add(new Point(p.c, -p.r));
        }
        
        result.sort((a, b) -> {
            if (a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
        
        return normalize(result);
    }
}