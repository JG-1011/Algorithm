import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 건물이 최소한 몇채인지 알아내는 프로그램
 *
 * 스택으로 높이 관리 해보자
 * 1. 현재 고도가 이전 고도보다 높아지면, 새로운 건물이 시작된 것으로 보고 스택 추가
 * 2. 이전 고도보다 낮아지면, 스택에서 이전 고도들을 제거해 건물의 끝을 판단
 * 3. 중복제거 : 같은 높이의 건물이 연속으로 나타나면 스킵
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>(); // 건물 높이를 저장하고 관리
        int buildingCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 스택이 비어있지 않고 현재값이 이전 건물 높이보다 낮을 때 > 건물이 끝나는 부분
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                buildingCnt++;
            }

            // 스택이 비어있지 않고 현재값이 이전 건물이랑 같으면 > 건물 이어지는 중
            if(!stack.isEmpty() && stack.peek() == y) continue;

            
            stack.push(y);

        }

        while (!stack.isEmpty()) {
            if (stack.pop() > 0) {
                buildingCnt++;
            }
        }

        System.out.println(buildingCnt);
    }
}