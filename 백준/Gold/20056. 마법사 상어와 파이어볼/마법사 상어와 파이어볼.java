import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * List<FireBall> grid = new ArrayList[N][N];
 * 이거와
 * List<List<FireBall>> grid = new ArrayList<>();
 * 차이를 정확하게 알고 싶음
 *
 * MoveFireball에서
 * List<FireBall> grid = new ArrayList[N][N]; 선언하고
 * MergeFireballs에서 다시 선언한 이유는 뭐야?
 * 그냥 Move에서 선언한거 그대로 가져다가 쓰면 안돼?
 * >> 쓰는 용도가 달라서 독립적으로 선언해서 써야 한다.
 * >> MoveFireball : 이동한 후의 파이어볼 위치와 속성을 임시로 저장하기 위한 용도
 * >> MergerFireball : 파이어볼이 겹쳐지는 위치에서 합치는 작업을 수행
 *
 * */


public class Main {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static List<FireBall> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new FireBall(r, c, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            MoveFireball();
            MergeFireball();
        }

        int ans = 0;
        for (FireBall fireBall : list) {
            ans += fireBall.m;
        }
        System.out.println(ans);
    }

    private static void MoveFireball() {
        List<FireBall>[][] grid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (FireBall fireBall : list) {
            int r = fireBall.r;
            int c = fireBall.c;
            int m = fireBall.m;
            int s = fireBall.s;
            int d = fireBall.d;

            int newR = ((r + dr[d] * s) % N + N) % N;
            int newC = ((c + dc[d] * s) % N + N) % N;

            grid[newR][newC].add(new FireBall(newR, newC, m, s, d));
        }

        list.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                list.addAll(grid[i][j]);
            }
        }

    }

    private static void MergeFireball() {
        List<FireBall>[][] grid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (FireBall fireBall : list) {
            grid[fireBall.r][fireBall.c].add(fireBall);
        }

        list.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j].size() >= 2) {
                    int sumM = 0, sumS = 0;
                    boolean allEven = true, allOdd = true;

                    for (FireBall fireBall : grid[i][j]) {
                        sumM += fireBall.m;
                        sumS += fireBall.s;
                        if (fireBall.d % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    int newM = sumM / 5;
                    if (newM == 0) continue;

                    int newS = sumS / grid[i][j].size();

                    int[] newDirs = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                    for (int k = 0; k < 4; k++) {
                        list.add(new FireBall(i, j, newM, newS, newDirs[k]));
                    }

                } else if (grid[i][j].size() == 1) {
                    list.add(grid[i][j].get(0));
                }
            }
        }
    }
}

class FireBall {
    int r, c, m, s, d;

    public FireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}