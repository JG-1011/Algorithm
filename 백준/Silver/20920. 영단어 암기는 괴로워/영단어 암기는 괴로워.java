import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제
 * 영어 단어 정렬을 다음과 같은 조건에 따라 수행합니다:
 * 1. 자주 나온 단어일수록 앞에 배치.
 * 2. 해당 단어의 길이가 길수록 앞에 배치.
 * 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치.
 *
 * M보다 짧은 단어는 제외하고 M 이상의 길이인 단어만 고려.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> hashMap = new HashMap<>();

        // 단어 입력받기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            // 단어의 길이가 M 미만이면 넘어감
            if (str.length() < M) {
                continue;
            }
            // 빈도수 카운팅
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }

        // HashMap의 keySet을 리스트로 변환

        List<String> bocas = new ArrayList<>(hashMap.keySet());

        // 3가지 정렬 기준을 반영하여 정렬 수행
        Collections.sort(bocas, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 1. 자주 나오는 단어일수록 앞에 배치 (빈도수 내림차순)
                int freqCompare = hashMap.get(o2).compareTo(hashMap.get(o1));
                if (freqCompare != 0) {
                    return freqCompare;
                }

                // 2. 단어의 길이가 길수록 앞에 배치 (길이 내림차순)
                int lengthCompare = Integer.compare(o2.length(), o1.length());
                if (lengthCompare != 0) {
                    return lengthCompare;
                }

                // 3. 알파벳 사전 순으로 정렬 (오름차순)
                return o1.compareTo(o2);
            }
        });

        // 결과 출력
        for (String word : bocas) {
            sb.append(word).append("\n");
        }

        System.out.println(sb);
    }
}
