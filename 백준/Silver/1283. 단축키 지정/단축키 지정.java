import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1. 첫번째 단어가 단축키인지 확인한다 > 안되어있으면 단축키 설정
 * 2. 모든 단어의 첫글자가 단축키면 왼쪽부터 찾아서 단축키 설정
 * 3. 불가능하면 그대로 둔다
 *
 * Set이용
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Set<Character> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String[] words = br.readLine().split(" ");
            boolean flag = false;

            for (int j = 0; j < words.length; j++) {
                char firstChar = Character.toLowerCase(words[j].charAt(0));

                if (!set.contains(firstChar)) {
                    set.add(firstChar);
                    words[j] = "[" + words[j].charAt(0) + "]" + words[j].substring(1);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                for (int j = 0; j < words.length; j++) {
                    for (int k = 0; k < words[j].length(); k++) {
                        char c = Character.toLowerCase(words[j].charAt(k));

                        if (!set.contains(c)) {
                            set.add(c);
                            words[j] = words[j].substring(0, k) + "[" + words[j].charAt(k) + "]" + words[j].substring(k + 1);
                            flag = true;
                            break;
                        }
                    }
                    if(flag) break;
                }
            }

            sb.append(String.join(" ", words)).append("\n");
        }

        System.out.println(sb);
    }
}
