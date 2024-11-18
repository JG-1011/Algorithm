import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 힙을 이용한 풀이
 * 특정 순위를 계산하고 싶다 >> 힙을 사용
 * 최소힙은 PriorityQueue를 이용한다.
 * 최소힙은 "전체정렬"이 아니라 최소힙의 조건을 만족하는 "부분정렬" 이다.
 * 최소힙의 조건 : 부모가 자식보다 작아야 한다.
 * 시간복잡도 : 삽입 > longN, 삭제 > logN 따라서 NlogN
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                minHeap.add(num);

                if (minHeap.size() > N) {
                    minHeap.poll(); // 가장 작은 수가 빠진다.
                }
            }
        }

        System.out.println(minHeap.peek()); // 맨 앞이 N번째로 큰 수가 된다.
    }
}
