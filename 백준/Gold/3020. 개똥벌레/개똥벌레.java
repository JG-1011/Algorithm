import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 및 StringTokenizer 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 전체 학생 수(N)와 복도의 높이(H)를 입력으로 받기
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        // 복도의 아래쪽과 위쪽 높이별로 각각의 구간을 나타내기 위한 배열 초기화
        int[] down = new int[H+2];
        int[] up = new int[H+2];

        // N/2번 반복하여 학생의 위치 정보를 입력받아 해당 위치의 아래쪽과 위쪽 배열 갱신
        for(int i = 0; i < N/2; i++) {
            down[Integer.parseInt(br.readLine())]++;
            up[Integer.parseInt(br.readLine())]++;
        }

        // 각 높이별로 아래쪽에서의 누적 값 및 위쪽에서의 누적 값 계산
        for(int i = H; i >= 1; i--) {
            down[i] += down[i+1];
        }
        for(int i = H; i >= 1; i--) {
            up[i] += up[i+1];
        }

        // 초기 최소값 및 해당 최소값의 개수를 나타내는 변수 초기화
        int min = N;
        int count = 0;

        // 각 높이에 대해 검사하여 최소값 및 최소값의 개수 갱신
        for(int i = 1; i <= H; i++) {
            int cnt = down[i] + up[H-i+1];
            if(cnt == min) {
                count++;
            }
            else if(min > cnt) {
                min = cnt;
                count = 1;
            }
        }

        // 최종 결과 출력
        System.out.println(min + " " + count);
    }
}