import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 해결방법
* 타겟을 먼저 1등으로 넣고 다른 나라와 하나하나 비교해가면서
* 타겟이 다른 나라보다 메달 수가 적을 때마다 +1을 해준다.
* 
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][4];
        int[] target = new int[3];  // 목표 국가의 메달 정보를 저장할 배열

        // 이차원 배열에 데이터 담기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

            if (arr[i][0] == K) {
                target[0] = arr[i][1]; // 목표 국가의 금메달 수
                target[1] = arr[i][2]; // 목표 국가의 은메달 수
                target[2] = arr[i][3]; // 목표 국가의 동메달 수
            }
        }

        int ans = 1; // 초기 등수는 1등으로 설정

        // 목표 국가와 다른 국가들을 비교하여 등수 계산
        for (int i = 0; i < N; i++) {
            if (arr[i][0] == K) {
                continue; // 목표 국가와는 비교하지 않음
            } else {
                if (arr[i][1] > target[0]) {
                    ans++;
                } else if (arr[i][1] == target[0]) {
                    if (arr[i][2] > target[1]) {
                        ans++;
                    } else if (arr[i][2] == target[1]) {
                        if (arr[i][3] > target[2]) {
                            ans++;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
