import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 단어의 개수
        String baseWord = br.readLine(); // 기준이 되는 첫 번째 단어
        int[] baseCount = getAlphabetCount(baseWord); // 첫 번째 단어의 알파벳 개수 배열
        
        int similarCount = 0;
        
        for (int i = 1; i < n; i++) {
            String word = br.readLine();
            int[] wordCount = getAlphabetCount(word);
            
            // 알파벳 개수 차이를 계산
            int difference = calculateDifference(baseCount, wordCount);
            
            // 비슷한 단어인지 판별
            if (isSimilar(difference, baseWord.length(), word.length())) {
                similarCount++;
            }
        }
        
        System.out.println(similarCount);
    }

    // 알파벳 개수 배열을 구하는 함수
    private static int[] getAlphabetCount(String word) {
        int[] count = new int[26]; // 알파벳 개수를 저장할 배열 (A-Z는 26개의 문자)
        for (char c : word.toCharArray()) {
            count[c - 'A']++;
        }
        return count;
    }

    // 두 개의 알파벳 개수 배열 간의 차이를 계산하는 함수
    private static int calculateDifference(int[] baseCount, int[] wordCount) {
        int difference = 0;
        for (int i = 0; i < 26; i++) {
            difference += Math.abs(baseCount[i] - wordCount[i]);
        }
        return difference;
    }

    // 비슷한 단어인지 판별하는 함수
    private static boolean isSimilar(int difference, int baseLength, int wordLength) {
        // 같은 구성을 갖는 경우
        if (difference == 0) return true;
        // 한 글자를 더하거나 빼서 같은 구성이 될 수 있는 경우
        if (difference == 1 && Math.abs(baseLength - wordLength) <= 1) return true;
        // 하나의 문자를 바꿔서 같은 구성이 될 수 있는 경우
        if (difference == 2 && baseLength == wordLength) return true;
        return false;
    }
}
