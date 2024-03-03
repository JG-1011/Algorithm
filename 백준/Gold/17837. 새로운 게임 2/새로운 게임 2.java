import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
포인트
1. indexof로 인덱스 찾고 subList로 나눠서 다루기
2. List<Integer>[][] list 리스트 2차배열로 만들고 다루기
3. List<Integer>[][] 이거와 List<List<Integer>> 차이점
    - List<Integer>[][] 이것은 진짜 이차배열로 만드는 것
    - List<List<Integer>> 이것은 각 행마다 길이가 다를 수 있다.
4. if (map[nr][nc] == 2 || nr < 0 || nc < 0 || nr >= N || nc >= N) {
    - map[nr][nc] ==2 를 앞에 넣으면 뒤에 있는 범위조건을 판단하지 않는다..
    - 그러므로 범위조건을 먼저 앞에 놓고 뒤에 map을 넣어야 한다
*/

class Horse {
    int r, c, d;

    public Horse(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "r=" + r +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}


public class Main {
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static int N, K, ans;
    static int[][] map;
    static List<Integer>[][] hLists;
    static List<Horse> horses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        hLists = new ArrayList[N][N];
        horses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                hLists[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            horses.add(new Horse(a, b, c));
            hLists[a][b].add(i);
        }

        ans = 0;
        boolean finished = false;
        loop:
        for (int t = 0; t < 1000; t++) {
            ans++;
            for (int i = 0; i < K; i++) {
                Horse horse = horses.get(i);
                int r = horse.r;
                int c = horse.c;
                int d = horse.d;

                int idx = hLists[r][c].indexOf(i);
                List<Integer> moveList = new ArrayList<>();
                List<Integer> stayList = new ArrayList<>();

                // 말위에 타고 있는 다른 말들과 같이 움직이기 위해 남아있을 말들과 움직일 말들 구분해주기
                for (int j = 0; j < hLists[r][c].size(); j++) {
                    if (j < idx) {
                        stayList.add(hLists[r][c].get(j));
                    } else {
                        moveList.add(hLists[r][c].get(j));
                    }
                }

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) {
                    if (d == 1) {
                        horse.d = 2;
                    } else if (d == 2) {
                        horse.d = 1;
                    } else if (d == 3) {
                        horse.d = 4;
                    } else {
                        horse.d = 3;
                    }

                    //다시 방향 탐색
                    nr = r + dr[horse.d];
                    nc = c + dc[horse.d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) {
                        continue; // 멈춘다
                    } else {
                        // 뒤집어서 말 좌표 이동해주고
                        if (map[nr][nc] == 1) {
                            Collections.reverse(moveList);
                        }
                        hLists[nr][nc].addAll(moveList);
                        for (Integer h : hLists[nr][nc]) {
                            horses.get(h).r = nr;
                            horses.get(h).c = nc;
                        }
                        hLists[r][c] = stayList;
                    }

                } else {
                    // 뒤집어서 말 좌표 이동해주고
                    if (map[nr][nc] == 1) {
                        Collections.reverse(moveList);
                    }
                    hLists[nr][nc].addAll(moveList);
                    for (Integer h : hLists[nr][nc]) {
                        horses.get(h).r = nr;
                        horses.get(h).c = nc;
                    }
                    hLists[r][c] = stayList;
                }
                if (hLists[nr][nc].size() >= 4) {
                    finished = true;
                    break loop;
                }
            } //1턴기준
        }
        System.out.println(finished ? ans : -1);
    }
}