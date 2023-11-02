import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, S, min; // 배열 크기, 목표 합, 최소 길이를 저장할 변수
	static int[] arr; // 입력된 숫자 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 배열 크기 입력
		S = Integer.parseInt(st.nextToken()); // 목표 합 입력

		arr = new int[N]; // 배열 초기화
		min = Integer.MAX_VALUE; // 최소 길이 값을 최대로 초기화

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 배열 요소 입력
		}

		int sum = arr[0]; // 현재까지 더한 합
		int end = 0; // 배열의 끝 인덱스
		for (int start = 0; start < N; start++) {
			while (end < N && sum < S) {
				end++;
				if (end != N)
					sum += arr[end]; // end를 증가시키며 sum에 해당 요소 추가
			}
			if (end == N)
				break; // end가 배열의 끝까지 도달하면 종료

			min = Math.min(min, end - start + 1); // 최소 길이 업데이트
			sum -= arr[start]; // start 요소를 합에서 빼줌
		}

		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}
}
