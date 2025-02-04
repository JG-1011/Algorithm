import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String text = br.readLine(); // 원본 문자열
        String bomb = br.readLine(); // 폭발 문자열
        int bombLen = bomb.length();
        
        StringBuilder sb = new StringBuilder(); // 스택 역할 수행
        
        for (char c : text.toCharArray()) {
            sb.append(c); // 문자 추가
            
            // 스택의 끝부분이 폭발 문자열과 같은지 확인
            if (sb.length() >= bombLen && sb.substring(sb.length() - bombLen).equals(bomb)) {
                sb.setLength(sb.length() - bombLen); // 폭발 문자열 제거
            }
        }
        
        // 최종 출력
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
