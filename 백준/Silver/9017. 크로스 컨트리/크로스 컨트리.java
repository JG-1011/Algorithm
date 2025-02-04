import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); // 참가자 수
            StringTokenizer st = new StringTokenizer(br.readLine());

            Map<Integer, List<Integer>> teamScores = new HashMap<>();
            Map<Integer, Integer> teamCount = new HashMap<>();
            List<Integer> validRanks = new ArrayList<>();

            List<Integer> originalTeams = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int team = Integer.parseInt(st.nextToken());
                originalTeams.add(team);
                teamScores.putIfAbsent(team, new ArrayList<>());
                teamScores.get(team).add(i);  // 초기 순위 저장 (0-based)
                teamCount.put(team, teamCount.getOrDefault(team, 0) + 1);
            }

            Map<Integer, Integer> adjustedRanks = new HashMap<>();
            int newRank = 0;
            for (int i = 0; i < N; i++) {
                int team = originalTeams.get(i);
                if (teamCount.get(team) >= 6) {
                    validRanks.add(team);
                    adjustedRanks.put(i, newRank++); // 새로운 순위 설정
                }
            }

            List<int[]> validTeams = new ArrayList<>();
            for (int team : teamScores.keySet()) {
                if (teamCount.get(team) >= 6) {
                    List<Integer> scores = teamScores.get(team);
                    int totalScore = adjustedRanks.get(scores.get(0)) +
                            adjustedRanks.get(scores.get(1)) +
                            adjustedRanks.get(scores.get(2)) +
                            adjustedRanks.get(scores.get(3));  // 상위 4명 점수 합
                    int fifthScore = adjustedRanks.get(scores.get(4)); // 5번째 선수 점수
                    validTeams.add(new int[]{team, totalScore, fifthScore});
                }
            }

            validTeams.sort((a, b) -> {
                if (a[1] == b[1]) return Integer.compare(a[2], b[2]); // 5번째 선수 점수 비교
                return Integer.compare(a[1], b[1]); // 4명 점수 합 비교
            });

            System.out.println(validTeams.get(0)[0]);
        }
    }
}
