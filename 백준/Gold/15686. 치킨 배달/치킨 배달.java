import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 쉬운문제였는데
// 최소값 구하는 과정에서 값 초기화 위치를 어디에 해야 하는지 헷갈려서 오래걸림
// 차근차근 봤으면 금방했을텐데 아쉽다..

public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static Point[] arr;
    static List<Point> chList;
    static List<Point> homList;
    static int[][] map;
    static int N, M, ans, min, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        chList = new ArrayList<>();
        homList = new ArrayList<>();
        arr = new Point[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homList.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chList.add(new Point(i, j));
                }
            }
        }

        ans = Integer.MAX_VALUE;
        combi(0, 0);
        System.out.println(ans);
    }

    private static void combi(int start, int depth) {
        if (depth == M) {
            sum = 0;
            int temp = 0;
            for (int i = 0; i < homList.size(); i++) {
                min = Integer.MAX_VALUE;
                Point home = homList.get(i);
                for (int j = 0; j < M; j++) {
                    temp = Math.abs(home.r - arr[j].r) + Math.abs(home.c - arr[j].c);
                    min = Math.min(min, temp);
                }
                sum += min;
            }
            ans = Math.min(sum, ans);
            return;
        }

        for (int i = start; i < chList.size(); i++) {
            arr[depth] = chList.get(i);
            combi(i + 1, depth + 1);
        }

    }
}