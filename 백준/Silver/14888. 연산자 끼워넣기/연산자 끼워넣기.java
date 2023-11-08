import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * - 문제
 *  N개로 이루어져 있음 1부터 시작함
 *  그리고 N-1개의 연산자가 주어진다 (더하기 빼기 곱 나누기)
 *	연선자 우선 순위를 고려하지 않고 그냥 앞에서부터 진행
 *	나누셈은 몫만 가진다 ( 만약에 음수를 양수로 나눌 때는 양수로 바꾸고 몫을 음수로 바꾼다)
 *
 *	최대, 최소의 결과값을 구해라
 *
 * 
 * - 조건
 *	수의 순서를 바꾸면 안돼
 * 
 * - 입력
 *	첫째줄 : N(수의 개수)
 *	둘째줄 : A(수 들)
 *	셋째줄 : 연사자의 개수들을 나타낸다 (+,-,*,/)
 * 
 * - 고민
 *	연산자의 순서를 어떻게 해야 최대값이 나올까?? >> 그냥 완탐으로 돌려보자(분기점이 있으니 dfs+백트래킹)
 *	
 * 	
 * 
 * 
 * */
public class Main {
	static int N, max, min;
	static int[] A, oper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		A = new int[N]; // 수를 담을 배열
		oper = new int[4]; // 연산자를 담을 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		dfs(A[0], 1);

		System.out.println(max);
		System.out.println(min);

	}

	// num : 수들의 합 , depth : 뽑은 숫자들의 수
	public static void dfs(int sum, int depth) {
		// 뽑은 수들과 N이 같아지면 종료
		if (depth == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}

		// 연산자 수만큼 for문 진행
		for (int i = 0; i < 4; i++) {
			// 만약 연산자의 수가 1개라도 있으면
			if (oper[i] > 0) {
				// 수를 줄이고
				oper[i]--;

				// i가 연산자 4개를 가르킨다.
				switch (i) {
				case 0:
					dfs(sum + A[depth], depth + 1); // dfs들어갈때마다 sum값 계산해주고 깊이는 하나씩 들어간다.
					break;
				case 1:
					dfs(sum - A[depth], depth + 1);
					break;
				case 2:
					dfs(sum * A[depth], depth + 1);
					break;
				case 3:
					dfs(sum / A[depth], depth + 1);
					break;
				}

				// dfs가 끝나면 다시 원복 (백트래킹)
				oper[i]++;
			}
		}
	}
}
