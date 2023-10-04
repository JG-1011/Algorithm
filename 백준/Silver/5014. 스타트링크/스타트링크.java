import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] visit;
    static int F, S, G, U, D; // F: 총 건물 층수, S: 내가 있는 층, G: 스타드링크 있는 층, U: 위로 올라가는 버튼으로 이동할 층수, D: 아래로 내려가는 버튼으로 이동할 층수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        visit = new int[1000000 + 1]; // 방문 여부를 저장할 배열

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); // 건물의 총 층수
        S = Integer.parseInt(st.nextToken()); // 시작 층
        G = Integer.parseInt(st.nextToken()); // 스타드링크가 있는 층
        U = Integer.parseInt(st.nextToken()); // 위로 올라가는 버튼으로 이동할 층수
        D = Integer.parseInt(st.nextToken()); // 아래로 내려가는 버튼으로 이동할 층수

        if (bfs(S)) {
            System.out.println(visit[G] - 1); // 스타드링크까지의 최소 이동 횟수 출력
        } else {
            System.out.println("use the stairs"); // 스타드링크에 도달할 수 없는 경우
        }
    }

    // 너비 우선 탐색(BFS)을 사용하여 최단 경로를 찾는 메서드
    public static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start); // 시작 층을 큐에 추가
        visit[start] = 1; // 시작 층을 방문 표시하고 이동 횟수를 1로 초기화

        while (!queue.isEmpty()) {
            int num = queue.poll(); // 큐에서 층을 하나 꺼냄

            if (num == G) { // 스타드링크 층에 도달했으면 종료
                return true;
            }

            for (int i = 0; i < 2; i++) { // U 버튼과 D 버튼을 눌렀을 때의 경우를 처리
                int next;
                if (i == 0) { // U 버튼을 누른 경우
                    next = num + U; // U만큼 올라감
                } else { // D 버튼을 누른 경우
                    next = num - D; // D만큼 내려감
                }

                if (next > 0 && next <= F && visit[next] == 0) { // 층이 범위 내에 있고 아직 방문하지 않았으면
                    queue.add(next); // 큐에 새로운 층 추가
                    visit[next] = visit[num] + 1; // 이동 횟수를 1 증가시켜서 저장
                }
            }
        }
        return false; // 스타드링크까지 도달할 수 없는 경우
    }
}
