import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 휴게소 N개 존재
 * 휴게소 위치는 출발점부터 얼마나 떨어져 있는지 알려준다
 * 휴게소 M개를 더 세우려고 한다
 *
 * 휴게소가 없는 구간의 // 길이의 최대값을 // 최소로 하려고 한다
 * 구간의 길이를 조정해야하잖아 >> 구간의 길이를 이분탐색으로 해보자
 *
 * */
public class Main {
    static int N, M, L;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.add(0);
        list.add(L);
        Collections.sort(list);

        System.out.println(binary());
    }

    private static int binary() {
        int left = 1;
        int right = L;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 여기에 카운트 초기화
            int cnt = 0;
            // 거리의 차를 구해서 mid만큼 나누어
            // 그리고 카운트에 더해
            // 만약에 나머지가 0 이면 마이너스 한번 해
            for (int i = 1; i < list.size(); i++) {
                int distance = list.get(i) - list.get(i - 1);
                cnt += distance / mid;
                if (distance % mid == 0) cnt--;
            }

            //여기서 만약에 카운트 > M 이면 mid값을 줄여(right = mid-1);
            if (M < cnt) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }

        return result;
    }
}