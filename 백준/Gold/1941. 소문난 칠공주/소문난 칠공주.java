import java.io.*;
import java.util.*;
 
public class Main {
    static int atoi(String str) {
        return Integer.parseInt(str);
    }
    static String A[][];
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static boolean visit[];
    static int select[];
    static int ans;
    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
 
    static void pro() {
        dfs(0,0, 0);
 
        System.out.println(ans);
    }
 
    static void dfs(int idx, int cnt,int cntS) {
        if(cnt == 7){
            if(cntS >= 4) {
                ans += bfs();
            }
            return;
        }
 
        for (int i = idx; i < 25; i++) {
            if(visit[i]) continue;
 
            select[cnt] = i;
            visit[i] = true;
            if(A[i/5][i%5].equals("S")) dfs(i+1, cnt + 1, cntS + 1);
            else dfs(i+1, cnt + 1, cntS);
            visit[i] = false;
            select[cnt] = -1;
        }
 
    }
 
    static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean vis[][] = new boolean[5][5];
        int cnt = 1;
        q.offer(select[0] / 5);
        q.offer(select[0] % 5);
        vis[select[0] / 5][select[0] % 5] = true;
 
        while (!q.isEmpty()) {
            int X = q.poll();
            int Y = q.poll();
 
            for (int i = 0; i < 4; i++) {
                int dX = X + dx[i];
                int dY = Y + dy[i];
 
                if(!isRangeTrue(dX,dY)) continue;
                if(vis[dX][dY]) continue;
                if(!visit[dX*5+dY]) continue;
                //dX*5+dY 번의 학생이 뽑혔는지 확인인
                cnt++;
                q.offer(dX);
                q.offer(dY);
                vis[dX][dY] = true;
            }
        }
 
        if(cnt == 7) return 1;
 
        else return 0;
 
    }
 
    static boolean isRangeTrue(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
 
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        A = new String[5][5];
        visit = new boolean[25];
        select = new int[7];
 
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                A[i][j] = str.charAt(j) + "";
            }
        }
    }
}
 