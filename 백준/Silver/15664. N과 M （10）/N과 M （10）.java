import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr, num;
	static boolean[] visit;
	static LinkedHashSet<String> hash;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// TreeSet을 사용 안한 이유, 자동 정렬을 해주지만 String형으로 저장되기 때문에
		// 14, 1321 두개가 들어가면 1321 14 순으로 저장됨
		hash = new LinkedHashSet<>();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N]; // 입력값을 담을 배열
		arr = new int[M]; // 뽑힌 값을 담을 배열
		visit = new boolean[N]; // 중복체크를 위해 ex) 1,1 2,2

		// 입력값 담기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(num);

		// 조합 드가자
		dfs(0, 0);
		hash.forEach(System.out::println);
	}

	public static void dfs(int start, int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder(); // 이걸 안에 사용해야지 출력이 잘된다
			for (int a : arr) {
				sb.append(a + " ");
			}
			hash.add(sb.toString());
			return;
		}

		for (int i = start; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = num[i];
				dfs(i + 1, depth + 1);
				visit[i] = false;
			}
		}
	}

}