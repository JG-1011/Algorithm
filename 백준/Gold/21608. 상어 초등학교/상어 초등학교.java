import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 자리배치까지는 완료 근데 마지막 계산하는 거 어떻게 해야함??
// 좋아하는 친구들 배열을 다른 곳에 또 저장을 해놔야하나??
class Seat {
    int r, c, blank, like;

    public Seat(int r, int c, int blank, int like) {
        this.r = r;
        this.c = c;
        this.blank = blank;
        this.like = like;
    }
}

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static class Point{
        int r,c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, student, sum;
    static int[][] map;
    static List<Seat> seatList; // Seat 정보 넣어둔 list
    static List<List<Integer>> sortList;
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        info = new int[N * N][5];
        sortList = new ArrayList<>();
        for (int i = 0; i <= N * N; i++) {
            sortList.add(new ArrayList<>());
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
                if (j > 0) {
                    sortList.get(info[i][0]).add(info[i][j]);
                }
            }
        }
        for (int i = 0; i < N * N; i++) {
            seatList = new ArrayList<>();
            student = info[i][0];

            search(i);
            map[seatList.get(0).r][seatList.get(0).c] = student;
            seatList.remove(0);
        }

        // 점수계산
        sum = 0;
        System.out.println(score());

    }

    private static int score() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        if (map[nr][nc] == sortList.get(map[i][j]).get(k)) {
                            cnt++;
                        }
                    }
                }
                if (cnt == 0) sum += 0;
                else if (cnt == 1) sum += 1;
                else if (cnt == 2) sum += 10;
                else if (cnt == 3) sum += 100;
                else if (cnt == 4) sum += 1000;
            }
        }
        return sum;
    }

    private static void search(int student) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int cnt = 0;
                    int cnt2 = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                            continue;
                        }
                        if (map[nr][nc] == 0) {
                            cnt++;
                        } else {
                            for (int k = 1; k <= 4; k++) {
                                if (map[nr][nc] == info[student][k]) {
                                    cnt2++;
                                }
                            }
                        }
                    }
                    seatList.add(new Seat(i, j, cnt, cnt2));
                }
            }
        }
        Collections.sort(seatList, new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                if (o2.like == o1.like) {
                    if (o2.blank == o1.blank) {
                        if (o1.r == o2.r) {
                            return o1.c - o2.c;
                        }
                        return o1.r - o2.r;
                    }
                    return o2.blank - o1.blank;
                }
                return o2.like - o1.like;
            }
        });
    }
}
