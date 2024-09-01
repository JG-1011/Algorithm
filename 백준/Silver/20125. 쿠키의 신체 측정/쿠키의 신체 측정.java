import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 해결방법
 * 머리는 무조건 한칸이니까 머리를 기점으로 심장을 찾고 직진을 해보자
 * 다리 같은 경우 대각선으로 이동 후 가보자
 * */
public class Main {
    static int N;
    static int[][] body;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Heart heart;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        body = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == '*') {
                    body[i][j] = 1;
                }
            }
        }

        //머리로 심장 찾기
        findHeart();
        findBodySize();
        System.out.println(sb);
    }

    private static void findBodySize() {
        int r = heart.r;
        int c = heart.c;
        //왼팔
        int lh = calculate(r, c - 1, 2);
        //오른팔
        int rh = calculate(r, c + 1, 3);
        //허리
        int wa = calculate(r + 1, c, 1);
        //왼다리
        int ll = calculate(r + wa + 1, c - 1, 1);
        //오른다리
        int rl = calculate(r + wa + 1, c + 1, 1);

        sb.append(r + 1).append(" ").append(c + 1).append("\n");
        sb.append(lh).append(" ").append(rh).append(" ").append(wa).append(" ").append(ll).append(" ").append(rl);

    }

    private static int calculate(int r, int c, int d) {
        int cnt = 0;
        //하
        if (d == 1) {
            for (int i = r; i < N; i++) {
                if (body[i][c] == 1) {
                    cnt++;
                }
            }
            //좌
        } else if (d == 2) {
            for (int i = c; i >= 0; i--) {
                if (body[r][i] == 1) {
                    cnt++;
                }
            }
            //우
        } else {
            for (int i = c; i < N; i++) {
                if (body[r][i] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void findHeart() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (body[i][j] == 1) {
                    heart = new Heart(i + 1, j);
                    return;
                }
            }
        }
    }
}

class Heart {
    int r, c;

    public Heart(int r, int c) {
        this.r = r;
        this.c = c;
    }
}