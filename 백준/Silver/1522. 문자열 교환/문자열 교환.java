import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 문자열
        String s = br.readLine();
        int n = s.length();

        // 전체 'a'의 개수 계산
        int totalA = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') totalA++;
        }

        // 슬라이딩 윈도우 초기화
        int currentA = 0;
        for (int i = 0; i < totalA; i++) {
            if (s.charAt(i) == 'a') currentA++;
        }

        // 최소 교환 횟수 초기화
        int minSwaps = totalA - currentA;

        // 슬라이딩 윈도우 이동
        for (int i = 1; i < n; i++) {
            // 이전 윈도우의 첫 번째 문자 제외
            if (s.charAt(i - 1) == 'a') currentA--;
            // 새로 추가되는 문자 포함
            if (s.charAt((i + totalA - 1) % n) == 'a') currentA++;

            // 최소 교환 횟수 갱신
            minSwaps = Math.min(minSwaps, totalA - currentA);
        }

        // 결과 출력
        System.out.println(minSwaps);
    }
}
