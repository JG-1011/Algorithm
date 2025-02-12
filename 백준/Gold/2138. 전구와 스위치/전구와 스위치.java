import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제
 * 스위치를 누르면 앞뒤자기자신 세개의 상태가 변경됨
 * 스위치 상태를 주어졌을 때 최소 몇번 눌러야 하는지 알아보자
 *
 * 해결
 * 앞에서부터 순차적으로 변환하면서 진행하는 "그리디" 가 최적화된 방법
 * 이전 전구가 목표하는 상태와 다르면, 현재 위치의 스위치를 누른다
 *
 *
 * */
public class Main {
    static int[] arr, target;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        target = new int[N];

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            arr[i] = str.charAt(i) - '0';
        }

        str = br.readLine();
        for (int i = 0; i < N; i++) {
            target[i] = str.charAt(i) - '0';
        }

        int noPressFirst = solve(false);
        int pressFirst = solve(true);

        int result = Math.min(noPressFirst, pressFirst);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int solve(boolean press) {
        int[] temp = arr.clone();
        int cnt = 0;

        if (press) {
            pressSwitch(temp, 0);
            cnt++;
        }

        for (int i = 1; i < N; i++) {
            if (temp[i - 1] != target[i - 1]) {
                pressSwitch(temp, i);
                cnt++;
            }
        }

        return isSameTarget(temp) ? cnt : Integer.MAX_VALUE;
    }

    private static boolean isSameTarget(int[] temp) {
        //하나라도 다르면 펄스 다 맞으면 트루
        for (int i = 0; i < N; i++) {
            if (temp[i] != target[i]) return false;
        }

        return true;
    }

    private static void pressSwitch(int[] temp, int index) {
        for (int i = index - 1; i <= index + 1; i++) {
            if (i >= 0 && i < N) {
                temp[i] ^= 1;
            }
        }
    }
}
