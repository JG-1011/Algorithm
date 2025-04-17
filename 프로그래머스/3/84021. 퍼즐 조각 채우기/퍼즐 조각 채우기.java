import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static List<List<Point>> spaceList = new ArrayList<>();
    static List<List<Point>> puzzleList = new ArrayList<>();
    
    static class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        boolean[][] visited = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0 && !visited[i][j]) {
                    List<Point> list = new ArrayList<>();
                    dfs(list, game_board, visited, n, i, j, 0);
                    spaceList.add(normalize(list));
                }
            }
        }
        
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    List<Point> list = new ArrayList<>();
                    dfs(list, table, visited, n, i, j, 1);
                    puzzleList.add(normalize(list));
                }
            }
        }
        
        boolean[] used = new boolean[puzzleList.size()];
        int answer = 0;
        
        for (List<Point> space : spaceList) {
            for (int i = 0; i < puzzleList.size(); i++) {
                if(used[i]) continue;
                List<Point> puzzle = puzzleList.get(i);
                if(space.size() != puzzle.size()) continue;
                
                boolean match = false;
                for (int r = 0; r < 4; r++) {
                    if(isSame(space, puzzle)) {
                        match = true;
                        break;
                    }
                    puzzle = rotate(puzzle);
                }
                
                if(match) {
                    used[i] = true;
                    answer += space.size();
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public static void dfs(List<Point> list, int[][] map, boolean[][] visited, int n, int startR, int startC, int target) {
        visited[startR][startC] = true;
        list.add(new Point(startR, startC));
        
        for (int d = 0; d < 4; d++) {
            int nr = startR + dr[d];
            int nc = startC + dc[d];
            
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (visited[nr][nc] || map[nr][nc] != target) continue;
            
            dfs(list, map, visited, n, nr, nc, target);
        }
    }
    
    public static List<Point> normalize(List<Point> list) {
        List<Point> tempList = new ArrayList<>();
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for (Point p : list) {
            minR = Math.min(minR, p.r);
            minC = Math.min(minC, p.c);
        }
        
        for (Point p : list) {
            tempList.add(new Point(p.r - minR, p.c - minC));
        }
        
        tempList.sort((a, b) -> {
            if (a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
        
        return tempList;
    }
    
    public static boolean isSame(List<Point> space, List<Point> puzzle) {
        for (int i = 0; i < space.size(); i++) {
            if(space.get(i).r != puzzle.get(i).r || space.get(i).c != puzzle.get(i).c) return false;
        }
        
        return true;
    }
    
    public static List<Point> rotate(List<Point> puzzle) {
        List<Point> tempList = new ArrayList<>();
        
        for (Point p : puzzle) {
            tempList.add(new Point(p.c, -p.r));
        }
        
        tempList.sort((a, b) -> {
            if (a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
        
        return normalize(tempList);
    }
}