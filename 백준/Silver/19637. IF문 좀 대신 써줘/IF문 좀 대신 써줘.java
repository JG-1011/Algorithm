import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제
 *
 * 고민거리
 * 해쉬맵으로할까? 리스트로할까?
 * 둘 다 사용
 * 해쉬맵은 타이틀과 점수를 저장하기 위해
 * 리스트는 이분탐색에 사용하기 위해
 *
 * Map<String, Integer> 로 선언하지 않고 Map<Integer, String>으로 선언한 이유는
 * 이분탐색에서 찾은 인덱스(Key)로 타이틀을 찾기 위해 바꿔서 사용
 *
 * 최대 고민거리
 * 어떻게 빠르게 조회를 할까?? for문을 사용했지만 오래걸렸다... "이분탐색"으로 빠르게 찾자
 * 
 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 * 틀렸다..
 * 두번째 예제 출력값이 B B C C C 가 나와야 하는데
 * A A C C C 가 나왔다..
 * 이유는 HashMap을 사용해서 중복을 허용하지 않았다.
 * 때문에 B,100을 저장하고 A,100을 저장하니 A로 덮어씌어졌다..
 * 선언을 HashMap<Integer,String>으로 진행해서..
 * 
 * 인사이트
 * 이분탐색을 이럴때도 사용 가능하다.
 * 
 * */
public class Main {
    static List<String> titles;
    static List<Integer> limits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        titles = new ArrayList<>();
        limits = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int limit = Integer.parseInt(st.nextToken());

            titles.add(title);
            limits.add(limit);
        }

        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            sb.append(findTitle(power)).append("\n");
        }

        System.out.println(sb);
    }

    private static String findTitle(int target) {
        int left = 0;
        int right = limits.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (target <= limits.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return titles.get(left);
    }
}