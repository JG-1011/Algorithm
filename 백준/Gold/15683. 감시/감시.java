import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 카메라를 배열에 담았어.. 인덱스가 cctv 번호 그 안에 값이 회전하는 수
// 근데 이걸 어떻게 돌려야 할 지 모르겠어
// 그리고 Point now = queue.poll(); 을 하면 다 뽑고 아무것도 없을 때 그만 둬야 하는 조건이 없어서
// 오류가 발생해.. 조건을 만들자니 바로 return을 해서 답이 제대로 안나오고 어떻게 해결해야 함?

// 해답
// cctv 번호 if문으로 나누고 그 안에서 또 d만큼 반복을 나눠
// for문 안에 있는 코드들은 한타임에 같이 일어난다... 여러방향을 관찰하는 것은 그냥 for문 안에 여러개를 넣으면 되는 거임
// 예를 들어 move()를 for문안에 세개를 넣으면 한번에 세방향씩 움직이는 거다 ㅇㅋ?

// queue.poll(); 해결 방법은 list로 바꿨다 그리고 큐로 했을 때는 직접 뽑아가면서 확인했다. list로 바꾸고 뽑지않고 get으로 안에 값만 확인하면서 돌렸다

//쪼개보자
// 입력값을 받는다.
// 이떄 값이 1-5사이이면 list에 넣는다.

// dfs를 시작한다.
// 기저조건 : depth가 list.size와 사이즈가 같으면 종료한다. > 가지고 있는 씨씨티비를 다 봤으니까
// 이때 0의 값을 카운트 해서 정답을 뽑아낸다.

// 재귀조건 : 먼저 카피맵을 만든다 그 이유는 백트래킹을 위해서 이전 걸로 원복 시키기 위해 (중복이 있으면 원복하기 힘드니까 맵을 복사해서 사용함)
// type을 가져와서 그 타입의 값만큼 for문을 돌릴거야
// 이때 타입별로 if문을 나눴다.
// move() 메소드를 만들어서 d 값에 따라 방향을 바꾸면서 앞으로 직진
// 돌았으면 dfs(depth+1)로 이동
// 그리고 원복 시킨다.

public class Main {

    //1번 cctv 4번 돌아라 2번 cctv 두번돌아라 이런 뜻
    static int[] cctv = {0, 4, 2, 4, 4, 1};

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};


    static int N, M, ans;
    static int[][] map;

    static class Point {
        int r, c, type;

        public Point(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static List<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    list.add(new Point(i, j, map[i][j]));
                }
            }
        }

        ans = Integer.MAX_VALUE;

        dfs(0);

        System.out.println(ans);
    }

    private static void dfs(int depth) {
        // cctv 개수만큼 들어가면 끝
        if (depth == list.size()) {
            //여기서 최소값을 구햅로까?사각지대의 최소값이니
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            if (ans > cnt) {
                ans = cnt;
            }
            return;
        }
        // 아 위에 코드 너무 아름다워 미쳤다 ㅅㅂ

        int[][] copyMap = new int[N][M];
//        큐로 구현했을 때 이걸 어떻게 해결함?
//        Point now = queue.poll();
        int type = list.get(depth).type;
        copy(copyMap, map);
        for (int d = 0; d < cctv[type]; d++) {
            if (type == 1) {
                move(list.get(depth), d);
            } else if (type == 2) {
                move(list.get(depth), d);
                move(list.get(depth), d + 2);
            } else if (type == 3) {
                move(list.get(depth), d);
                move(list.get(depth), d + 1);
            } else if (type == 4) {
                move(list.get(depth), d);
                move(list.get(depth), d + 1);
                move(list.get(depth), d + 3);
            } else {
                move(list.get(depth), d);
                move(list.get(depth), d + 1);
                move(list.get(depth), d + 2);
                move(list.get(depth), d + 3);
            }
            //map가지고 놀다가 (copyMap이 원본을 가지고 있다가 돌려주는 것으로 생각)
            dfs(depth + 1);
            //이전상태로 돌리기
            copy(map, copyMap);
        }
    }

    private static void move(Point now, int d) {
        d = d % 4; // 이거 생각하는 거 대박... 개천재들인가?
        if (d == 0) {
            for (int i = now.r; i >= 0; i--) {
                if (map[i][now.c] == 6) {
                    break;
                } else {
                    map[i][now.c] = -1;
                }
            }
        } else if (d == 1) {
            for (int i = now.c; i < M; i++) {
                if (map[now.r][i] == 6) {
                    break;
                } else {
                    map[now.r][i] = -1;
                }
            }
        } else if (d == 2) {
            for (int i = now.r; i < N; i++) {
                if (map[i][now.c] == 6) {
                    break;
                } else {
                    map[i][now.c] = -1;
                }
            }
        } else {
            for (int i = now.c; i >= 0; i--) {
                if (map[now.r][i] == 6) {
                    break;
                } else {
                    map[now.r][i] = -1;
                }
            }
        }

    }

    private static void copy(int[][] copyMap, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }
}