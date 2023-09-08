import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://st-lab.tistory.com/194
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		double sum = 0; //
		double ans = 0;

		// 조약돌 색 수
		int M = Integer.parseInt(br.readLine());

		int[] arr = new int[M]; // 색상별 조약돌 개수를 배열에 담기
		double[] result = new double[M]; // 각각 나올 확률을 담는 배수

		// 조약돌 개수 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}

		// 뽑을 개수
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= K) { // 색상별 조약돌이 선택하는 개수보다 많아야 다 뽑힐 확률이 생긴다, 만약에 색상별 조약돌 개수가 뽑힐 개수보다 적으면 다 뽑힐 확률이
								// 0이므로 K보다 크거나 같아야 한다.
				result[i] = 1.0; // 곱하기 한 값을 넣어야 하는데 1로 초기화를 하지 않으면 곱해도 0 이 나오니까
				for (int j = 0; j < K; j++) {
					result[i] *= (arr[i] - j) / (sum - j);
				}
				ans += result[i];
			}
		}
		System.out.println(ans);
	}
}