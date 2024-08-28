import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 조건
 * 모음이 있는지 없는지 판단 > boolean
 * 모음 자음 연속 3개 오면 안된다 > cnt를 통해서 ++하고 다른거 오면 0으로 만들어보자
 * 같은 글자인지 아닌지 이전글자 확인 > 이게 문제네
 *
 * String으로 받고 char로 분할해서 배열에 저장해
 * for문을 char배열 크기만큼 돌아
 * char c = 꺼낸문자
 * if(모음이면) true
 * if(자음이면) else(모음이면) 카운트++하고 반대카운트0으로 변경;
 * if(자음카운터||모음카운터 3개면 break)
 * 
 * 해결방법
 * 일단 e와 o가 두번만 가능한 것이 아니라 eeeee... ooooo... 계속해서 사용 가능함
 * 두가지로 풀어봄
 * 1. 먼저 아래 코드에 작성되어 있는 것처럼 prevChar를 이용해서
 * 2. 두번째는
         if (i >= 1) {
             if (arr[i - 1] == arr[i]) {
                 if (arr[i] == 'e') {
                     continue;
                 } else if (arr[i] == 'o') {
                     continue;
                 }
                 doubleInARowCheck = true;
                 break;
             }
         }
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;

            if (isPossible(str)) {
                sb.append("<").append(str).append(">").append(" ").append("is acceptable.").append("\n");

            } else {
                sb.append("<").append(str).append(">").append(" ").append("is not acceptable.").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isPossible(String str) {
        int gatherCnt = 0;
        int consonantCnt = 0;
        boolean containGather = false;
        char prevChar = '\0';

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (isGather(c)) {
                containGather = true;
                gatherCnt++;
                consonantCnt = 0;
            } else {
                consonantCnt++;
                gatherCnt = 0;
            }

            if (gatherCnt == 3 || consonantCnt == 3) {
                return false;
            }

            if (c == prevChar && c != 'e' && c != 'o') {
                return false;
            }

            prevChar = c;
        }
        return containGather;
    }

    private static boolean isGather(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}