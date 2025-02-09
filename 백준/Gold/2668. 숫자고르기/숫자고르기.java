import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 세로 두 줄, 가로 N 줄로 이루어진 표가 있음
 * 첫째 줄에서 숫자를 뽑았을 때 아래 줄에도 숫자가 일치해야 함
 * 최대로 뽑는 방법은 무엇일까
 *
 * 해결
 * 완탐 > 부분집합으로 xxx 실패
 * dfs로 사이클을 확인해서 시작점에서 출발해서 시작점으로 돌아온다면 위 아래가 같다는 것
 * ㄴ 사이클을 돌렸을 때 시작점과 끝점이 연결되어 있는지 확인해야 함
 *
 * 문제점
 * 첫째줄 번호와 둘째줄 번호 같은지 확인하는 방법 어떻게 할거임?
 * 배열 만들어서 하나하나 확인해?
 */
public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited, inCycle;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        inCycle = new boolean[N + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    private static void dfs(int start, int current) {
        if (visited[current]) {
            if (current == start) {
                result.add(start);
                inCycle[start] = true;
            }
            return;
        }

        visited[current] = true;
        dfs(start, arr[current]);
    }
}