import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M, min;  // N: 배열 크기, M: 차이의 최솟값, min: 결과값
    static int[] arr;  // 입력된 배열을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 배열 크기 입력
        M = Integer.parseInt(st.nextToken());  // 차이의 최솟값 입력

        arr = new int[N];  // 크기 N의 배열 선언
        min = Integer.MAX_VALUE;  // 결과값 초기화

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());  // 배열 요소 입력
        }

        Arrays.sort(arr);  // 배열을 정렬

        int end = 0;  // 배열의 끝 인덱스를 나타내는 변수
        for (int start = 0; start < N; start++) {
            while (end < N && arr[end] - arr[start] < M) {
                end++;  // 차이가 M 미만인 동안 end를 증가시킴
            }
            if (end == N) {
                break;  // end가 배열 끝까지 도달하면 종료
            }
            min = Math.min(min, arr[end] - arr[start]);  // min 값을 업데이트
        }

        System.out.println(min);  // 결과값 출력
    }
}
