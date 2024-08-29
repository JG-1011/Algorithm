import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
* 해결방법
* 중복제거를 해야한다 > set 사용
* Hashmap vs Hashset
*
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashSet<String> hashSet = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        for (int i = 0; i < N; i++) {
            hashSet.add(br.readLine());
        }

        int ans = 0;
        int cnt = hashSet.size();

        if (game.equals("Y")) {
            ans = cnt ;
        } else if (game.equals("F")) {
            ans = cnt / 2;
        } else {
            ans = cnt / 3;
        }

        System.out.println(ans);

    }
}