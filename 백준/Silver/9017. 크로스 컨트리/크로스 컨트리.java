import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 개인성적 + 팀성적
 * 한팀에 6명
 * 상위 네명 주자의 점수를 합해서 계산
 * 다섯번째도 알아야 한다
 *
 * 팀별로 개인의 점수를 알아야 한다.
 * 팀원이 여섯명이 되는지 알아야 한다.
 * 새로운 등수를 알아야 한다.
 *
 * 문제점
 * Map<Integer,List<Integer>> 를 했을때 List에 add하는 방법을 모르겠음
 * 존재하는 모든 Map을 불러와야 함 어케해
 *
 * */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            Map<Integer, Integer> teamCnt = new HashMap<>();
            int N = Integer.parseInt(br.readLine());
            int[] map = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                map[i] = num;
                teamCnt.put(num, teamCnt.getOrDefault(num, 0) + 1);
            }

            Map<Integer, List<Integer>> teamRank = new HashMap<>();
            int newRank = 1;
            for (int i = 0; i < N; i++) {
                int team = map[i];
                if (teamCnt.get(team) == 6) {
                    teamRank.putIfAbsent(team, new ArrayList<>());
                    teamRank.get(team).add(newRank++);
                }
            }

            //팀끼리 계산해야 한다. >> 모든 Map을 불러오는 메서드 필요
            List<int[]> vaildTeam = new ArrayList<>();
            for (int team : teamCnt.keySet()) {
                if (teamCnt.get(team) == 6) {
                    List<Integer> teamInfo = teamRank.get(team);
                    int totalScore = teamInfo.get(0) +
                            teamInfo.get(1) +
                            teamInfo.get(2) +
                            teamInfo.get(3);
                    int fiveTeam = teamInfo.get(4);

                    vaildTeam.add(new int[]{team, totalScore, fiveTeam});
                }
            }

            vaildTeam.sort((a, b) -> {
                if (a[1] == b[1]) return Integer.compare(a[2], b[2]);
                return Integer.compare(a[1], b[1]);
            });

            System.out.println(vaildTeam.get(0)[0]);
        }
    }
}