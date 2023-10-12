import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Time {
    int t;
    char d;

    public Time(int t, char d) {
        this.t = t;
        this.d = d;
    }
}

public class Main2 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static List<Time> timeList = new ArrayList<>();
    static Deque<Point> deque = new LinkedList<>();

    static boolean[][] map;
    static boolean[][] appleMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());  // 보드의 크기 N을 입력
        int K = Integer.parseInt(br.readLine());  // 사과의 개수 K를 입력

        map = new boolean[N + 1][N + 1];  // 뱀의 위치를 나타내는 맵
        appleMap = new boolean[N + 1][N + 1];  // 사과의 위치를 나타내는 맵

        // 입력값 받기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int appleR = Integer.parseInt(st.nextToken());  // 사과의 행 위치
            int appleC = Integer.parseInt(st.nextToken());  // 사과의 열 위치
            appleMap[appleR][appleC] = true;  // 해당 위치에 사과가 있음을 표시
        }

        int L = Integer.parseInt(br.readLine());  // 방향 변환 횟수 L을 입력
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            timeList.add(new Time(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));  // 시간과 방향 정보 저장
        }

        Collections.sort(timeList, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.t - o2.t;  // 시간순으로 정렬
            }
        });

        deque.add(new Point(1, 1));  // 뱀의 시작 위치
        map[1][1] = true;  // 뱀이 위치한 곳을 표시
        int r = 1;
        int c = 1;
        int d = 1;  // 시작 방향 (동쪽)
        int ans = 0;  // 경과된 시간

        while (true) {
            // 먼저 뱀은 몸길이를 늘려 머리를 다음 칸에 위치시킨다.
            // 만약 벽이나 자기 자신의 몸과 부딪히면 게임이 끝난다.
            int nr = r + dr[d];  // 다음 위치 계산
            int nc = c + dc[d];
            if (nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc]) {
                ans++;  // 충돌 시 게임 종료 처리
                break;
            }
            deque.add(new Point(nr, nc));  // 뱀의 머리 위치 갱신
            map[nr][nc] = true;  // 뱀이 위치한 곳 표시
            ans++;  // 경과 시간 증가

            // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            if (appleMap[nr][nc]) {
                appleMap[nr][nc] = false;  // 사과를 먹었으므로 사과 위치 갱신

                // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
            } else {
                Point p = deque.pollFirst();  // 꼬리 위치 갱신
                map[p.r][p.c] = false;  // 꼬리가 위치한 곳 비우기
            }

            // 방향 전환 처리
            loop:for (int i = 0; i < timeList.size(); i++) {
                if (ans == timeList.get(i).t) {  // 시간이 지정된 시간과 일치하면 방향 전환
                    switch (timeList.get(i).d) {
                        case 'L':
                            d = (d + 3) % 4;  // 왼쪽으로 90도 회전
                            break loop;
                        case 'D':
                            d = (d + 1) % 4;  // 오른쪽으로 90도 회전
                            break loop;
                    }
                }
            }
            r = nr;  // 뱀의 현재 위치 갱신
            c = nc;
        }

        System.out.println(ans);  // 게임이 종료된 후 경과된 시간 출력
    }
}
