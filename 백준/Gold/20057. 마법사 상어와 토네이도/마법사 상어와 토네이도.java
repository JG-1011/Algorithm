import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int outSand = 0;

    // 토네이도 이동 방향: 좌, 하, 우, 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    // 모래가 퍼지는 비율 및 위치 (좌 기준)
    static int[][][] spreadPositions = {
            {{-1, 1}, {1, 1}, {-2, 0}, {2, 0}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {0, -2}}, // 좌
            {{-1, -1}, {-1, 1}, {0, -2}, {0, 2}, {0, -1}, {0, 1}, {1, -1}, {1, 1}, {2, 0}}, // 하
            {{-1, -1}, {1, -1}, {-2, 0}, {2, 0}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {0, 2}}, // 우
            {{1, 1}, {1, -1}, {0, 2}, {0, -2}, {0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {-2, 0}}  // 상
    };
    static int[] spreadRatio = {1, 1, 2, 2, 7, 7, 10, 10, 5}; // 퍼지는 비율

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도 시뮬레이션 수행
        tornado();
        System.out.println(outSand);
    }

    static void tornado() {
        int x = N / 2, y = N / 2; // 시작점: 격자 중앙
        int steps = 1; // 이동 거리
        int dir = 0; // 초기 방향: 좌

        while (true) {
            for (int i = 0; i < 2; i++) { // 같은 칸 수로 두 번 이동
                for (int j = 0; j < steps; j++) {
                    x += dx[dir];
                    y += dy[dir];

                    // 격자 밖으로 나간 경우 종료
                    if (x < 0 || y < 0 || x >= N || y >= N) return;

                    // 모래 퍼뜨리기
                    spreadSand(x, y, dir);
                }
                dir = (dir + 1) % 4; // 방향 전환
            }
            steps++; // 이동 거리 증가
        }
    }

    static void spreadSand(int x, int y, int dir) {
        int totalSand = map[x][y];
        int spreadTotal = 0;

        // 비율에 따라 모래 퍼뜨리기
        for (int i = 0; i < 9; i++) {
            int nx = x + spreadPositions[dir][i][0];
            int ny = y + spreadPositions[dir][i][1];
            int spreadAmount = (totalSand * spreadRatio[i]) / 100;

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                outSand += spreadAmount; // 격자 밖으로 나간 모래 합산
            } else {
                map[nx][ny] += spreadAmount; // 격자 안에 모래 추가
            }
            spreadTotal += spreadAmount;
        }

        // 알파 위치 계산
        int alphaX = x + dx[dir];
        int alphaY = y + dy[dir];
        int alphaSand = totalSand - spreadTotal;

        if (alphaX < 0 || alphaY < 0 || alphaX >= N || alphaY >= N) {
            outSand += alphaSand; // 격자 밖으로 나간 모래 합산
        } else {
            map[alphaX][alphaY] += alphaSand; // 격자 안에 추가
        }

        map[x][y] = 0; // 현재 위치 모래 제거
    }
}
