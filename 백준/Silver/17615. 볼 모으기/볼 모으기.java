import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int n = Integer.parseInt(br.readLine());
        String balls = br.readLine();

        // 색상별 전체 개수
        int redCount = 0;
        int blueCount = 0;
        for (char ball : balls.toCharArray()) {
            if (ball == 'R') redCount++;
            else blueCount++;
        }

        // 4가지 경우의 이동 횟수를 계산
        int minMoves = Integer.MAX_VALUE;

        // 1. 왼쪽으로 모두 빨간색(R) 몰기
        int leftRedMoves = countMoves(balls, 'R', true);
        minMoves = Math.min(minMoves, leftRedMoves);

        // 2. 왼쪽으로 모두 파란색(B) 몰기
        int leftBlueMoves = countMoves(balls, 'B', true);
        minMoves = Math.min(minMoves, leftBlueMoves);

        // 3. 오른쪽으로 모두 빨간색(R) 몰기
        int rightRedMoves = countMoves(balls, 'R', false);
        minMoves = Math.min(minMoves, rightRedMoves);

        // 4. 오른쪽으로 모두 파란색(B) 몰기
        int rightBlueMoves = countMoves(balls, 'B', false);
        minMoves = Math.min(minMoves, rightBlueMoves);

        // 결과 출력
        System.out.println(minMoves);
    }

    // 특정 색깔로 공을 몰 때 필요한 이동 횟수 계산
    private static int countMoves(String balls, char color, boolean toLeft) {
        int moves = 0;

        if (toLeft) {
            // 왼쪽에서부터 연속된 해당 색상의 공 건너뛰기
            int i = 0;
            while (i < balls.length() && balls.charAt(i) == color) {
                i++;
            }
            // 남은 해당 색상의 공 개수
            for (int j = i; j < balls.length(); j++) {
                if (balls.charAt(j) == color) moves++;
            }
        } else {
            // 오른쪽에서부터 연속된 해당 색상의 공 건너뛰기
            int i = balls.length() - 1;
            while (i >= 0 && balls.charAt(i) == color) {
                i--;
            }
            // 남은 해당 색상의 공 개수
            for (int j = 0; j <= i; j++) {
                if (balls.charAt(j) == color) moves++;
            }
        }

        return moves;
    }
}
