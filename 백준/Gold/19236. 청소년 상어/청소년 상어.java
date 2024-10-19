import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0; // 상어가 먹은 물고기 번호 합의 최대값

    // 물고기 클래스
    static class Fish {
        int r, c, dir;
        boolean isAlive;

        public Fish(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isAlive = true;
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

        // 상어 이동
        // 0,0에 있는 물고기 정보를 저장해놔야 함
        int startId = map[0][0];
        int initialSum = startId;
        Fish startFish = fishes[startId];
        startFish.isAlive = false;
        int initialDir = startFish.dir;
        map[0][0] = -1;

        dfs(map, fishes, 0, 0, initialDir, initialSum);

        System.out.println(maxSum);

    }

    private static void dfs(int[][] map, Fish[] fishes, int sharkR, int sharkC, int sharkD, int eatSum) {
        maxSum = Math.max(maxSum, eatSum);

        int[][] copyMap = new int[4][4];
        Fish[] copyFishes = new Fish[17];
        BackUp(map, copyMap, fishes, copyFishes);

        moveAllFish(copyMap, copyFishes);

        //상어이동
        for (int step = 1; step <= 3; step++) {
            int nr = sharkR + dr[sharkD] * step;
            int nc = sharkC + dc[sharkD] * step;

            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || copyMap[nr][nc] == 0) continue;

            int fishId = copyMap[nr][nc];

            copyFishes[fishId].isAlive = false;
            copyMap[sharkR][sharkC] = 0;
            copyMap[nr][nc] = -1;

            dfs(copyMap, copyFishes, nr, nc, copyFishes[fishId].dir, eatSum + fishId);

            copyFishes[fishId].isAlive = true;
            copyMap[sharkR][sharkC] = -1;
            copyMap[nr][nc] = fishId;

        }

    }

    private static void moveAllFish(int[][] map, Fish[] fishes) {
        for (int i = 1; i <= 16; i++) {
            Fish now = fishes[i];
            if (!now.isAlive) continue;

            int r = now.r;
            int c = now.c;
            int dir = now.dir;

            for (int d = 0; d < 8; d++) {
                int nd = (dir + d) % 8;
                int nr = r + dr[nd];
                int nc = c + dc[nd];

                if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || map[nr][nc] == -1) continue;

                if (map[nr][nc] == 0) {
                    map[r][c] = 0;
                    map[nr][nc] = i;
                    now.r = nr;
                    now.c = nc;
                    now.dir = nd;
                } else {
                    int otherFishId = map[nr][nc];
                    map[r][c] = otherFishId;
                    map[nr][nc] = i;
                    now.r = nr;
                    now.c = nc;
                    now.dir = nd;
                    fishes[otherFishId].r = r;
                    fishes[otherFishId].c = c;
                }
                break;
            }
        }
    }


    private static void BackUp(int[][] map, int[][] copyMap, Fish[] fishes, Fish[] copyFishes) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < 17; i++) {
            Fish originalFish = fishes[i];
            if (originalFish != null) {
                copyFishes[i] = new Fish(originalFish.r, originalFish.c, originalFish.dir);
                copyFishes[i].isAlive = originalFish.isAlive;
            }
        }
    }
}
