import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 풀이
// 먼저 1~4의 톱니바퀴 배열3과 7을 비교해서 서로 상극인지 아닌지 먼저 체크를 하고
// 방향 배열을 만들어 상극인지 아닌지 알 수 있는 배열을 통해 회전배열을 채워넣으려고 했어
// 근데 만약에 3번 회전방향을 알게 되어도 1 2 4에 방향을 집어넣어야 할 지 모르겠음..
// 어케해야 하지? 흠흠


public class Main {
    static int[][] map;
    static int[] lotaion;
    static int K, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][9];

        for (int i = 1; i <= 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j + 1] = str.charAt(j) - '0';
            }
        }

        ans = 0;

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            lotaion = new int[5];
            //타겟검사
            lotaion[target] = direction;
            //왼쪽 검사
            for (int left = target - 1; left >= 1; left--) {
                int right = left + 1;
                if (map[left][3] == map[right][7]) {
                    break;
                } else {
                    lotaion[left] = lotaion[right] * -1;
                }
            }
            //오른쪽 검사
            for (int right = target + 1; right <= 4; right++) {
                int left = right - 1;
                if (map[left][3] == map[right][7]) {
                    break;
                } else {
                    lotaion[right] = lotaion[left] * -1;
                }
            }

            //배열이동
            for (int j = 1; j <= 4; j++) {
                move(j, lotaion[j]);
            }
        }

        //계산
        for (int i = 1; i <= 4; i++) {
            if (map[i][1] == 1) {
                if (i == 1) {
                    ans += 1;
                } else if (i == 2) {
                    ans += 2;
                } else if (i == 3) {
                    ans += 4;
                } else if (i == 4) {
                    ans += 8;
                }
            }
        }
        System.out.println(ans);
    }

    private static void move(int num, int direction) {
        if (direction == 1) {
            int temp = map[num][8];
            for (int j = 8; j > 1; j--) {
                map[num][j] = map[num][j - 1];
            }
            map[num][1] = temp;
        } else if (direction == -1) {
            int temp = map[num][1];
            for (int j = 1; j < 8; j++) {
                map[num][j] = map[num][j + 1];
            }
            map[num][8] = temp;
        }
    }
}
