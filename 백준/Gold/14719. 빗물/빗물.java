import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());  // 세로 길이
        int W = Integer.parseInt(st.nextToken());  // 가로 길이

        boolean[][] map = new boolean[H][W];
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j = 0; j < height; j++) {
                map[H - 1 - j][i] = true;  // 각 블록의 높이를 true로 표시
            }
        }

        // 각 행을 순차적으로 확인하면서 빗물의 양을 계산
        for (int i = 0; i < H; i++) {
            boolean inBetweenWalls = false;  // 벽 사이를 찾기 위한 플래그
            int countWater = 0;  // 현재 행에서 고일 수 있는 물의 양

            for (int j = 0; j < W; j++) {
                if (map[i][j]) {
                    // 벽을 만나면
                    if (inBetweenWalls) {
                        // 이전 벽과 현재 벽 사이에 고인 물의 양을 추가
                        ans += countWater;
                    }
                    // 벽을 만났으니 새로운 구간을 시작해야 하므로, 물의 양 초기화
                    countWater = 0;
                    inBetweenWalls = true;
                } else if (inBetweenWalls) {
                    // 벽을 이미 만났고 빈 공간을 만나면 물을 채울 수 있음
                    countWater++;
                }
            }
        }

        System.out.println(ans);
    }
}
