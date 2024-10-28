import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 상어에 1~M 자연수가 있음
 * 1이 가장 강력함
 *
 * NxN 격자
 *
 * 자신의 자리에 냄새를 뿌림
 * 냄새는 상어가 K번 이동하면 사라짐
 *
 * 상어의 이동방향은
 * 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다
 * 그런 칸이 없으면 "자신의 냄새가 있는 칸의 방향"으로 잡는다
 * 이떄 여러방향일 수 있는데 > 이럴때는 표를 참조해 이동해
 *
 * 이동후
 * 한 칸에 여러 마리 상어가 남아있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로
 *
 *
 * 해결
 * 지도에 상어 투입
 * 1번이 남을때까지 반복{
 * 상어이동
 * 겹치는지 확인 > 삭제 >> 이떄 하나가 남는지 확인
 * 초시간++
 * }
 * sout
 *
 * 뭘 사용해야 할까
 * List[][] ??
 * shark클래스는 번호 냄새 r c
 *
 * 상어번호 냄새카운터
 * 현재 상어 위치 지도를 만들어야겠다
 *
 *
 * */

public class Main {
    static class Shark {
        int id, y, x, dir;
        int[][] priority = new int[5][5]; // 상어의 이동 우선순위

        Shark(int id, int y, int x, int dir) {
            this.id = id;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    private static int N, M, K, time = 0;
    private static Shark[] sharks;
    private static int[][][] smell; // 냄새 정보
    private static int[] dy = {0, -1, 1, 0, 0};
    private static int[] dx = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharks = new Shark[M + 1];
        smell = new int[N][N][2];  // 냄새 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int sharkId = Integer.parseInt(st.nextToken());
                if (sharkId > 0) {
                    sharks[sharkId] = new Shark(sharkId, i, j, 0);
                    smell[i][j][0] = sharkId; // 냄새 주인 상어 ID
                    smell[i][j][1] = K;       // 냄새 지속 시간
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            for (int d = 1; d <= 4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int p = 1; p <= 4; p++) {
                    sharks[i].priority[d][p] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(simulate());
    }

    private static int simulate() {
        while (time++ < 1000) {
            moveSharks();
            reduceSmell();
            addSmell();

            if (countSharks() == 1) return time; // 상어가 한 마리 남으면 종료
        }
        return -1; // 1000초 초과시 -1 반환
    }

    private static void moveSharks() {
        int[][] nextPosition = new int[N][N];  // 다음 위치 저장 배열

        for (int i = 1; i <= M; i++) {
            Shark s = sharks[i];
            if (s == null) continue;

            boolean moved = false;
            int newDir = s.dir;

            // 빈 칸 우선 이동
            for (int p = 1; p <= 4; p++) {
                newDir = s.priority[s.dir][p];
                int ny = s.y + dy[newDir];
                int nx = s.x + dx[newDir];

                if (isInBounds(ny, nx) && smell[ny][nx][1] == 0) {
                    s.y = ny;
                    s.x = nx;
                    s.dir = newDir;
                    moved = true;
                    break;
                }
            }

            // 빈 칸이 없다면 자신의 냄새 있는 칸으로 이동
            if (!moved) {
                for (int p = 1; p <= 4; p++) {
                    newDir = s.priority[s.dir][p];
                    int ny = s.y + dy[newDir];
                    int nx = s.x + dx[newDir];

                    if (isInBounds(ny, nx) && smell[ny][nx][0] == s.id) {
                        s.y = ny;
                        s.x = nx;
                        s.dir = newDir;
                        break;
                    }
                }
            }

            // 이동 후 충돌 시 상어 처리
            if (nextPosition[s.y][s.x] == 0 || nextPosition[s.y][s.x] > s.id) {
                nextPosition[s.y][s.x] = s.id;
            } else {
                sharks[s.id] = null;
            }
        }

        // 충돌된 위치에 상어 업데이트
        for (int i = 1; i <= M; i++) {
            Shark s = sharks[i];
            if (s != null && nextPosition[s.y][s.x] != s.id) {
                sharks[s.id] = null;
            }
        }
    }

    private static void reduceSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smell[i][j][1] > 0) smell[i][j][1]--; // 냄새 지속 시간 감소
                if (smell[i][j][1] == 0) smell[i][j][0] = 0; // 냄새가 사라지면 상어 정보 제거
            }
        }
    }

    private static void addSmell() {
        for (int i = 1; i <= M; i++) {
            Shark s = sharks[i];
            if (s != null) {
                smell[s.y][s.x][0] = s.id; // 새로운 냄새 주인 상어 ID
                smell[s.y][s.x][1] = K;    // 새로운 냄새 지속 시간
            }
        }
    }

    private static int countSharks() {
        int count = 0;
        for (int i = 1; i <= M; i++) {
            if (sharks[i] != null) count++;
        }
        return count;
    }

    private static boolean isInBounds(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}