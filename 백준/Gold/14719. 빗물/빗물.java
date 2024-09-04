import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 해결방법
 * 핵심
 * 1. 왼쪽 벽과 오른쪽 벽 사이에 빈 공간이 있을 때만 빗물이 고인다.
 * 2. 왼쪽과 오른쪽 벽 중에 가장 낮은 벽의 높이를 기준으로 물이 고인다.
 *
 * 두번째 시도 (투포인트)
 * 빗물이 고이는 방식
 * 1. 각 열에서 빗물이 고일 수 있는 최대 높이 = min(왼쪽에서 가장 높은 벽, 오른쪽에서 가장 높은 벽)
 * 2. 실제로 고일 수 있는 물의 양 = 위에서 계산한 높이 - 현재 블럭의 높이
 *
 * 단계별 해결 전략
 * 1. 각 열마다 왼쪽에서 가장 높은 벽과 오른쪽에서 가장 높은 벽을 구한다.
 * 2. 왼쪽과 오른쪽에서 더 낮은 벽을 기준으로 물이 고일 수 있는 높이를 구하고, 현재 블록의 높이를 뺀다.
 * 3. 그 값을 모든 열에 대해 더하면 총 빗물의 양이 나온다.
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken()); // 세로 길이 H
        int W = Integer.parseInt(st.nextToken()); // 가로 길이 W

        int[] height = new int[W]; // 각 열의 높이를 저장할 배열

        // 높이 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        // 왼쪽에서 각 열까지의 최대 높이를 저장하는 배열
        int[] leftMax = new int[W];
        leftMax[0] = height[0];
        for (int i = 1; i < W; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 오른쪽에서 각 열까지의 최대 높이를 저장하는 배열
        int[] rightMax = new int[W];
        rightMax[W - 1] = height[W - 1];
        for (int i = W - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 각 열에 고일 수 있는 물의 양 계산
        for (int i = 0; i < W; i++) {
            // 물의 양은 왼쪽 최대 높이와 오른쪽 최대 높이 중 작은 값에서 현재 블록 높이를 뺀 값
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        System.out.println(ans);
    }
}
