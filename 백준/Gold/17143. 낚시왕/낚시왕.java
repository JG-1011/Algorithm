import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
    int s, d, z;

    public Shark(int s, int d, int z) {
        this.s = s;
        this.d = d;
        this.z = z;
    }
}

public class Main {
    static int[] dr = {0, -1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽
    static int[] dc = {0, 0, 0, 1, -1};

    static int R, C, M, r, c, s, d, z, ans;
    static Shark[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            map[r][c] = new Shark(s, d, z);
        }

        ans = 0;
        for (int j = 1; j <= C; j++) {
            for (int i = 1; i <= R; i++) {
                if (map[i][j] != null) {
                    ans += map[i][j].z;
                    map[i][j] = null;
                    break;
                }
            }

            Shark[][] backup = new Shark[R + 1][C + 1];
            copy(backup, map);
            moveShark(backup);
        }

        System.out.println(ans);
    }

    private static void moveShark(Shark[][] backup) {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {

                if (backup[i][j] != null) {
                    Shark shark = backup[i][j];
                    int r = i;
                    int c = j;
                    int moveDistance;
                    if (shark.d == 1 || shark.d == 2) {
                        moveDistance = shark.s % ((R - 1) * 2); //상하이동
                    } else {
                        moveDistance = shark.s % ((C - 1) * 2); //좌우이동
                    }

                    for (int k = 0; k < moveDistance; k++) {
                        //처음부터 붙어있을 때가 있네
                        if (shark.d == 1) {
                            r--;
                            if (r < 1) {
                                shark.d = 2;
                                r = 2;
                            }
                        } else if (shark.d == 2) {
                            r++;
                            if (r > R) {
                                shark.d = 1;
                                r = R - 1;
                            }
                        } else if (shark.d == 3) {
                            c++;
                            if (c > C) {
                                shark.d = 4;
                                c = C - 1;
                            }
                        } else {
                            c--;
                            if (c < 1) {
                                shark.d = 3;
                                c = 2;
                            }
                        }
                    }

                    if (map[r][c] != null && map[r][c].z > shark.z) {
                        continue;
                    }
                    map[r][c] = shark;
                }
            }
        }

    }

    private static void copy(Shark[][] backup, Shark[][] map) {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                backup[i][j] = map[i][j];
                map[i][j] = null;
            }
        }
    }
}