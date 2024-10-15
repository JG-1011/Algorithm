import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 문제
 * NxN 공간에
 * 물고기 M마리와 아기상어 1마리 존재
 * 한 칸에 물고기 0 or 1마리
 * 상어 크기 : 2로 시작
 * 1초에 상하좌우로 이동 > 사방탐색
 * 상어보다 큰 물고기는 지나갈 수 없음, 나머지는 가능
 * 상어보다 작은 물고기만 먹을 수 있다, 크기가 같으면 먹x 지나가o
 *
 * 상어 이동 방법
 * 1. 먹을 수 있는 물고기가 없으면 엄마 상어에게 도움 요청
 * 2. 먹을 수 있는 물고기가 1마리면 먹으로 간다
 * 3. 1마리보다 많으면 가까운 물고기 먹으러 간다.
 *  가까운 물고기가 여러마리면 가장 위에 있는 물고기,
 *  위에 있는 물고기 많으면 가장 왼쪽 물고기를 먹는다
 *
 * 4. 물고기 먹으면 물고기 사라짐
 * 5. 물고기 개수 = 상어크기 >> 1 증가
 *
 * 해결
 * 1. 상어 클래스 선언 : 크기, 좌표
 * 2. 상어와의 거리 어떻게 계산할까? >> bfs로 탐색해보자 방향은 위쪽부터 ?
 *
 * 상어이동을 담을 클래스 하나
 * 물고기 정보를 담을 클래스 하나
 *
 *
 * while문(무한)
 * 물고기 찾기
 * 현재 상어 위치에서 BFS진행 > flag(t:있음,f:없음)
 * 물고기 없으면 종료
 * 물고기 있으면 먹기
 *
 *
 *
 */


public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int sharkSize = 2;
    static int sharkEatCount = 0;
    static int time = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int sharkR = 0, sharkC = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Shark nextShark = findFish(sharkR, sharkC);
            if (nextShark == null) {
                break;
            }

            sharkR = nextShark.r;
            sharkC = nextShark.c;
            time += nextShark.dist;
            sharkEatCount++;

            if (sharkEatCount == sharkSize) {
                sharkSize++;
                sharkEatCount = 0;
            }

            map[sharkR][sharkC] = 0;
        }

        System.out.println(time);

    }

    private static Shark findFish(int startR, int startC) {
        Queue<Shark> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Shark(startR, startC, 0));
        visited[startR][startC] = true;

        List<Shark> fishList = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Shark now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
                    continue;
                }

                //상어보다 작아야해
                //상어보다 크면 패스
                //상어랑 같아도 패스
                //현재 상어크기보다 크면 패스
                if (sharkSize < map[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] > 0 && map[nr][nc] < sharkSize) {
                    if (now.dist + 1 <= minDist) {
                        minDist = now.dist + 1;
                        fishList.add(new Shark(nr, nc, now.dist + 1));
                    }
                }

                queue.add(new Shark(nr, nc, now.dist + 1));
                visited[nr][nc] = true;
            }
        }

        if (fishList.isEmpty()) {
            return null;
        }

        Collections.sort(fishList, (f1, f2) -> {
            if (f1.dist == f2.dist) {
                if (f1.r == f2.r) {
                    return f1.c - f2.c;
                }
                return f1.r - f2.r;
            }
            return f1.dist - f2.dist;
        });

        return fishList.get(0);
    }

}

class Shark {
    int r, c, dist;

    public Shark(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}