import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* 좋은문제인듯
*
* HashMap을 통해 "" 팀번호, 팀원들의 등수 "" 를 같이 저장
* 또 팀원 수를 카운트 하는 HashMap을 만들어서 "" 팀번호, 카운트 "" 를 같이 저장
*
* Map을 사용한 이유는 updateRanks에서 기존 등수를 새로운 등수로 매핑하기 위해서 사용했다.
* Set을 이용하면 매핑 불가능, 점수 계산할 때 새로운 등수를 찾을 방법이 없다.
* Map : key-value 묶어서 저장 가능 (key만 중복 불가능 value값은 중복 가능)
* Set : 중복을 허용하지 않고, 특정 요소가 존재하는지만 판단
* ㄴ 정리하자면 중복을 막으려면 HashSet을 사용하고, 키-값 매핑이 필요하면 HashMap을 사용
*
* Map인터페이스를 구현하는 대표적 세가지 클래스 존재(HashMap, TreeMap, LinkedHashMap)
* HashMap : 빠른조회, 순서보장x
* TreeMap : 자동정렬 지원
* LinkedHashMap : 입력 순서 유지
*
* 정렬 필요없고, 순서 유지도 필요없고, 빠른 조회로 매핑만 하면되니까 > HashMap으로 구현
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            Map<Integer, List<Integer>> teamScores = new HashMap<>(); // 각 팀의 선수 초기 순위 저장
            Map<Integer, Integer> teamCnt = new HashMap<>(); // 각 팀의 출전 선수 수 저장
            List<Integer> originalTeams = new ArrayList<>(); // 입력값 저장

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int team = Integer.parseInt(st.nextToken());
                originalTeams.add(team);
                teamScores.putIfAbsent(team, new ArrayList<>());
                teamScores.get(team).add(i);
                teamCnt.put(team, teamCnt.getOrDefault(team, 0) + 1);
            }

            // 6명 이상 팀만 새로운 순위 부여
            Map<Integer, Integer> updateRanks = new HashMap<>();
            int newRank = 0;
            for (int i = 0; i < N; i++) {
                int team = originalTeams.get(i);
                if (teamCnt.get(team) >= 6) {
                    updateRanks.put(i, newRank++); // 기존 등수 i를 새로운 등수 newRank로 매핑한다
                }
            }

            List<int[]> validTeams = new ArrayList<>();
            for (int team : teamScores.keySet()) {
                if (teamCnt.get(team) >= 6) {
                    List<Integer> scores = teamScores.get(team);
                    int totalScore = updateRanks.get(scores.get(0)) +
                            updateRanks.get(scores.get(1)) +
                            updateRanks.get(scores.get(2)) +
                            updateRanks.get(scores.get(3));
                    int fifthScore = updateRanks.get(scores.get(4));
                    validTeams.add(new int[]{team, totalScore, fifthScore});
                }
            }

            validTeams.sort((a, b) -> {
                if(a[1] == b[1]) return Integer.compare(a[2], b[2]);
                return Integer.compare(a[1], b[1]);
            });

            System.out.println(validTeams.get(0)[0]);
        }
    }
}
