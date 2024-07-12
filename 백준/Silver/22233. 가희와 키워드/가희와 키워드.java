import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
 
 
public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 줄을 읽고 StringTokenizer를 통해 공백으로 분리
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        // 메모장에 적은 키워드 개수(N)와 블로그에 쓴 글의 개수(M)를 변수에 저장
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        // KeywordCounter 객체 생성
        KeywordCounter keywordCounter = new KeywordCounter();
 
        // 메모장에 적은 키워드 추가
        keywordCounter.addKeywords(br, N);
 
        // 블로그 글 처리
        keywordCounter.processBlogPosts(br, M);
    }
}
 
// KeywordCounter 클래스
class KeywordCounter {
    // 키워드를 저장할 해시맵
    private HashMap<String, Integer> keywords;
 
    // constructor
    public KeywordCounter() {
        keywords = new HashMap<>();
    }
 
    // 메모장에 적은 키워드를 해시맵에 추가하는 메서드
    public void addKeywords(BufferedReader br, int N) throws IOException {
        // N번 반복하면서 키워드를 읽고 해시맵에 저장
        for (int i = 0; i < N; i++) {
            String keyword = br.readLine();
            keywords.put(keyword, 1);
        }
    }
 
    // 블로그 글을 처리하는 메서드
    public void processBlogPosts(BufferedReader br, int M) throws IOException {
        // M번 반복하면서 블로그에 사용된 키워드를 읽고 처리
        for (int i = 0; i < M; i++) {
            // 한 줄을 읽어와서 ','로 분리
            String line = br.readLine();
            String[] tokens = line.split(",");
            // 토큰마다 해시맵에서 제거
            for (String token : tokens) {
                if (keywords.containsKey(token)) {
                    keywords.remove(token);
                }
            }
            // 남은 키워드 수 출력
            System.out.println(keywords.size());
        }
    }
}