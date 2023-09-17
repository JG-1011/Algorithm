import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { 0, 0, -1, 1, 1, 1, -1, -1 }; // 상하좌우 다음 시계방향으로
    static int[] dc = { 1, -1, 0, 0, 1, -1, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String king = st.nextToken();
        int kingX = king.charAt(0) - 'A' + 1;
        int kingY = king.charAt(1) - '0';
        String stone = st.nextToken();
        int stoneX = stone.charAt(0) - 'A' + 1;
        int stoneY = stone.charAt(1) - '0';

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            int d = trans(br.readLine());

            // 자리 x좌표는 r로 y좌표는 c로 통일
            int r = kingX;
            int c = kingY;
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 1 && nr <= 8 && nc >= 1 && nc <= 8) {
                if (nr == stoneX && nc == stoneY) {
                    if (stoneX + dr[d] >= 1 && stoneX + dr[d] <= 8 && stoneY + dc[d] >= 1 && stoneY + dc[d] <= 8) {
                        stoneX += dr[d]; // 교환
                        stoneY += dc[d];
                    } else {
                        continue; // continue 있어야 밑으로 내려가서 갱신 안하고 다시 for문 돌음
                    }
                }
                kingX = nr; // 서로 바뀌어 있었음
                kingY = nc;
            }
        }

        king = "";
        stone = "";

        StringBuilder sb = new StringBuilder();
        sb.append((char) (kingX + 'A' - 1)).append(kingY).append("\n");
        sb.append((char) (stoneX + 'A' - 1)).append(stoneY);

        System.out.println(sb);
    }

    public static int trans(String d) {
        if (d.equals("T")) {  // 0,1 교환
            return 0;
        } else if (d.equals("B")) {
            return 1;
        } else if (d.equals("L")) {
            return 2;
        } else if (d.equals("R")) {
            return 3;
        } else if (d.equals("RT")) {  // 수정
            return 4;
        } else if (d.equals("RB")) {  // 수정
            return 5;
        } else if (d.equals("LT")) {  // 6,7 교환
            return 6;
        } else {
            return 7;
        }
    }
}