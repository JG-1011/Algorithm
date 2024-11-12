import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 타노스 0과 1로 이루어진 문자열 S를 보았다.
 * 0과 1의 개수는 짝수이다
 * 절반의 0과 절반의 1을 제거하여 새로운 S를 만들었다.
 * 사전순으로 가장 빠른 것을 구해라
 *
 * 0은 뒤에서부터 빼고 1은 앞에서부터 빼면 되는데
 * 0과 1의 개수는 알아야 하고
 * 각각의 위치도 알아야 하고
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        int[] isDeleted = new int[str.length()];
        char[] cArr = str.toCharArray();
        int zeroCnt = 0;
        int oneCnt = 0;

        for (char c : cArr) {
            if (c == '0') zeroCnt++;
            if (c == '1') oneCnt++;
        }

        zeroCnt /= 2;
        oneCnt /= 2;

        for (int i = 0; i < cArr.length; i++) {
            if (oneCnt == 0) break;
            if (cArr[i] == '1') {
                isDeleted[i] = 1;
                oneCnt--;
            }
        }

        for (int i = cArr.length - 1; i >= 0; i--) {
            if (zeroCnt == 0) break;
            if (cArr[i] == '0') {
                isDeleted[i] = 1;
                zeroCnt--;
            }
        }

        for (int i = 0; i < cArr.length; i++) {
            if (isDeleted[i] == 1) continue;
            sb.append(cArr[i]);
        }

        System.out.println(sb);

    }
}
