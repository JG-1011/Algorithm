import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 작은놈이 앞, 큰놈이 뒤
 * 키가 같은놈 없다
 * 임의로 한놈을 뽑는다
 * 그리고 맨뒤에 세운다
 * 자기 앞에 키큰 학생이 없으면 그 자리에 서고 차례가 끝난다
 * 키큰 학생이 한명이라도 있으면 그 중 가장 앞에 있는 학생 바로 앞에 선다.
 * 이때 그 뒤에 학생들은 한발씩 물러선다.
 * 학생들이 총 몇번 뒤로 물러설까?
 *
 * 해결방법
 * 역순 쌍의 수를 이용
 * i<j 이면서 arr[i] > arr[j] 인 경우를 역순 쌍이라고 한다.
 * 앞에  있는 학생 중 키가 큰 아이가 있다는 것은 > 배열에서 arr[i] > arr[j]가 만족하는 상황이므로
 * 끼어들게 되는 학생이 상황을 바로잡는 과정에서 물러나는 걸음 수를 역순 쌍의 개수로 세면 된다.
 *
 *
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] arr;
        int ans;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            arr = new int[20];
            ans = 0;

            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 20; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[i]) ans++;
                }
            }

            sb.append(P).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);

    }//tc

}
