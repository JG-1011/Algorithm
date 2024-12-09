import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashSet<Character> usedKeys = new HashSet<>(); // 사용된 단축키를 추적

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] words = str.split(" ");
            boolean assigned = false;

            // 1. 각 단어의 첫 글자에서 단축키 찾기
            for (int j = 0; j < words.length; j++) {
                char firstChar = Character.toLowerCase(words[j].charAt(0));
                if (!usedKeys.contains(firstChar)) {
                    usedKeys.add(firstChar); // 단축키로 등록
                    words[j] = "[" + words[j].charAt(0) + "]" + words[j].substring(1); // 단축키 설정
                    assigned = true;
                    break; // 단축키 설정 완료 시 종료
                }
            }

            // 2. 각 단어 전체에서 단축키 찾기
            if (!assigned) {
                for (int j = 0; j < words.length; j++) {
                    for (int k = 0; k < words[j].length(); k++) {
                        char currentChar = Character.toLowerCase(words[j].charAt(k));
                        if (!usedKeys.contains(currentChar)) {
                            usedKeys.add(currentChar); // 단축키로 등록
                            words[j] = words[j].substring(0, k) + "[" + words[j].charAt(k) + "]" + words[j].substring(k + 1);
                            assigned = true;
                            break;
                        }
                    }
                    if (assigned) break; // 단축키 설정 완료 시 종료
                }
            }

            // 3. 결과 문자열 생성
            sb.append(String.join(" ", words)).append("\n");
        }

        // 최종 출력
        System.out.print(sb);
    }
}
