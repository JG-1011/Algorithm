import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
//배열 만들 때 +1
//처음 양분 5만큼 들어있음 (초기화)
//봄 : 나이만큼 양분을 먹고 > 나이 1증가
//봄-1 : 만약 여러 나무가 있다면 어린나무부터(정렬을 해야할 것 같음) 양분을 먹고 만약 영양분이 부족하면 양분을 먹지 못하고 죽는다.
//여름 : 봄에 죽은 나무가 양분으로 변하게 된다. 죽은 나무마다 나이를 2로 나는 값을 더한다 (소수 버려)
//가을 : 나무 나이가 5의 배수이면 인접8곳에 번식
//겨울 : 기계 돌아다니면서 양분을 추가

//핵심포인트
//spring 구현에서 나무가 중복으로 들어갔을 때 글에서 어린나부부터.. 라는 말이 보였을 때 정렬을 하면 되는구나 라고 생각을 했어야 했는데
//못해서 헤맨부분.. 그리고 list로 구현했을 때 나무가 죽었을 때 dieTreeList에만 넣고 treeList에서도 삭제를 했어야 했는데 안한점
//list에서 삭제하면 for each나 list.size()에 인덱스가 사라져서 순서가 떙겨짐 그래서 순서가 꼬이는 점을 생각해야 한다.
//iterator를 사용하면 해결할 수 있다.. 컬렉션을 for문 돌리고 싶을때 (수정 삭제가 바로바로 된다)

public class Main {
    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 8방향 상대 좌표 (상, 하, 좌, 우, 대각선)
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int[][] map, S2D2; // 지도 및 겨울에 추가되는 양분 배열

    static List<Tree> treeList = new LinkedList<>();; // 살아있는 나무 리스트
    static List<Tree> dieTreeList, newTreeList; // 죽은 나무 리스트

    static int N, M, K; // 배열 크기, 나무 개수, 연도 수

    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열 크기
        M = Integer.parseInt(st.nextToken()); // 나무 개수
        K = Integer.parseInt(st.nextToken()); // K년

        // 초기화: 지도에 초기 양분 5 설정
        map = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = 5;
            }
        }

        // 겨울 양분 정보 입력
        S2D2 = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            treeList.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < K; i++) {
            // 어린 나무부터 실행하기 위해 정렬
            Collections.sort(treeList);
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(treeList.size());
    }

    // 봄: 나무가 양분을 먹고 나이가 1 증가
    public static void spring() {
        dieTreeList = new ArrayList<>();
        newTreeList = new ArrayList<>();

        Iterator<Tree> iterator = treeList.iterator();
        while (iterator.hasNext()) {
            Tree tree = iterator.next();
            if (map[tree.r][tree.c] >= tree.age) {
                map[tree.r][tree.c] -= tree.age;
                tree.age += 1;
                if (tree.age % 5 == 0) {
                    newTreeList.add(new Tree(tree.r, tree.c, tree.age));
                }
            } else {
                // 양분이 부족하면 죽은 나무 리스트에 추가하고 나무 리스트에서 제거
                dieTreeList.add(new Tree(tree.r, tree.c, tree.age));
                iterator.remove();
            }
        }
    }

    // 여름: 죽은 나무가 양분으로 변하고, 나이의 절반만큼 양분 추가
    public static void summer() {
        for (int i = 0; i < dieTreeList.size(); i++) {
            map[dieTreeList.get(i).r][dieTreeList.get(i).c] += dieTreeList.get(i).age / 2;
        }
    }

    // 가을: 나이가 5의 배수인 나무가 주변 8개 칸에 번식
    public static void autumn() {
        for (Tree tree : newTreeList) {
            for (int d = 0; d < 8; d++) {
                int nr = tree.r + dr[d];
                int nc = tree.c + dc[d];

                if (nr >= 1 && nc >= 1 && nr < N + 1 && nc < N + 1) {
                    treeList.add(new Tree(nr, nc, 1));
                }
            }
        }
    }

    // 겨울: 기계 돌아다니면서 양분을 추가
    public static void winter() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] += S2D2[i][j];
            }
        }
    }
}