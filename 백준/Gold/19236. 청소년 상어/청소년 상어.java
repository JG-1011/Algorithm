import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상, 상좌, 좌, 좌하, 하, 하우, 우, 상우
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0; // 상어가 먹은 물고기 번호 합의 최대값

    // 물고기 클래스
    static class Fish {
        int x, y, dir;
        boolean isAlive;

        Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = true;
        }

        // 깊은 복사를 위한 생성자
        Fish(Fish f) {
            this.x = f.x;
            this.y = f.y;
            this.dir = f.dir;
            this.isAlive = f.isAlive;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[4][4]; // 격자 지도
        Fish[] fishes = new Fish[17]; // 물고기 정보 (1~16번)

        // 입력 받기
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1; // 방향 인덱스 0부터 시작
                fishes[num] = new Fish(i, j, dir);
                map[i][j] = num;
            }
        }

        // 상어가 (0,0)의 물고기를 먹음
        int startFishId = map[0][0];
        int initialSum = startFishId;
        Fish startFish = fishes[startFishId];
        startFish.isAlive = false;
        int initialDir = startFish.dir;
        map[0][0] = -1; // 상어의 위치 표시

        // DFS 시작
        dfs(map, fishes, 0, 0, initialDir, initialSum);
        System.out.println(maxSum);
    }

    // DFS 함수
    static void dfs(int[][] map, Fish[] fishes, int sharkX, int sharkY, int sharkDir, int eatSum) {
        maxSum = Math.max(maxSum, eatSum);

        // 현재 상태 복사 (백트래킹을 위해 깊은 복사)
        int[][] copyMap = new int[4][4];
        Fish[] copyFishes = new Fish[17];
        for (int i = 0; i < 4; i++) {
            copyMap[i] = map[i].clone();
        }
        for (int i = 1; i <= 16; i++) {
            copyFishes[i] = new Fish(fishes[i]);
        }

        // 물고기 이동
        moveAllFish(copyMap, copyFishes);

        // 상어 이동 (1, 2, 3 칸 이동 가능)
        for (int step = 1; step < 4; step++) {
            int nx = sharkX + dx[sharkDir] * step;
            int ny = sharkY + dy[sharkDir] * step;

            // 격자 범위 내에 있고, 물고기가 있는 칸일 경우
            if (isIn(nx, ny) && copyMap[nx][ny] > 0) {
                int fishId = copyMap[nx][ny];
                Fish eatenFish = copyFishes[fishId];
                copyFishes[fishId].isAlive = false;
                copyMap[sharkX][sharkY] = 0; // 상어의 이전 위치는 빈 칸으로
                copyMap[nx][ny] = -1; // 상어의 새로운 위치

                // 재귀 호출
                dfs(copyMap, copyFishes, nx, ny, eatenFish.dir, eatSum + fishId);

                // 복원 (백트래킹)
                copyFishes[fishId].isAlive = true;
                copyMap[nx][ny] = fishId;
                copyMap[sharkX][sharkY] = -1;
            }
        }
    }

    // 모든 물고기 이동
    static void moveAllFish(int[][] map, Fish[] fishes) {
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].isAlive) continue;

            int x = fishes[i].x;
            int y = fishes[i].y;
            int dir = fishes[i].dir;

            for (int j = 0; j < 8; j++) {
                int nd = (dir + j) % 8;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (isIn(nx, ny) && map[nx][ny] != -1) { // 상어가 있는 칸은 피함
                    // 빈 칸일 경우
                    if (map[nx][ny] == 0) {
                        map[x][y] = 0;
                        map[nx][ny] = i;
                        fishes[i].x = nx;
                        fishes[i].y = ny;
                        fishes[i].dir = nd;
                    }
                    // 다른 물고기가 있는 경우 교환
                    else {
                        int otherFishId = map[nx][ny];
                        map[x][y] = otherFishId;
                        map[nx][ny] = i;
                        fishes[i].x = nx;
                        fishes[i].y = ny;
                        fishes[i].dir = nd;
                        fishes[otherFishId].x = x;
                        fishes[otherFishId].y = y;
                    }
                    break; // 이동 완료
                }
            }
        }
    }

    // 격자 내에 있는지 확인
    static boolean isIn(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }
}
