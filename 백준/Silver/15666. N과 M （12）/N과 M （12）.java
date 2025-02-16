import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 중복 조합
 *
 * visited은 중복허용 유무에 따라 결정된다.
 * start도 중복방지가 가능하다 + 이전 값보다 작은 값은 선택할 수 없도록 조정가능
 *
 * ex) N = 4, M = 2
 * 순열(visited[] 사용)
 * 1 2
 * 1 3
 * 1 4
 * 2 1
 * 2 3
 * 2 4
 * 3 1
 * 3 2
 * 3 4
 * 4 1
 * 4 2
 * 4 3
 *
 * 조합(start 사용)
 * 1 2
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 3 4
 *
 * start를 이용하면 중복 선택을 막을 수 있는 경우 visited[]를 사용하지 않아도 됨
 * 순열은 선택한 숫자를 다시 선택할 수 없으므로 visited[]가 필요하고
 * 조합은 이미 선택한 숫자 이후만 탐색하면 되므로 start만 사용 가능
 *
 * */
public class Main {
    static int N, M;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        combi(0, 0);

        System.out.println(sb);
    }

    private static void combi(int start, int depth) {
        if (depth == M) {
            for (int num : list) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = start; i < N; i++) {
            if (arr[i] != prev) {
                list.add(arr[i]);
                prev = arr[i];

                combi(i, depth + 1);

                list.remove(list.size() - 1);
            }
        }
    }
}