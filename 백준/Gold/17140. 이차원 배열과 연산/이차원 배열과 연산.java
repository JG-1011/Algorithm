import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 여기서 배열의 크기를 알아보고 다른 숫자만큼 x2 배열 크기를 만들어
// 그리고 하나하나 수 카운트 세면서 만들어야 하나?
// 오래 걸리지 않을까?

// 문제 접근은
// 먼저 전체적인 틀을 만들다
// while(map[r][c]!=k)일때까지 계속 돌면서 if문으로 행과 열 길이를 비교하면서 어떤 정렬을 할 것인가 , 탈출조건을 먼저 큰틀로 잡아놓고
// 그다음 정렬을 구현한다.
// 정렬 구현은 한행을 먼저 어떻게 구현 할 지 생각한 다음 한 행의 구현이 완성되면 for문으로 전체행을 훑는다..

// 배운점.. 배열의 크기를 정할 때 숫자크기만큼 x2해서 배열의 크기를 자유자재로 선언하면서 하면 메모리 걱정이 커서 시도를 못했다..
// 근데 보면서 그냥 배열의 최대 크기가 100이니까 처음부터 101(1부터시작하니까 101)로 선언해놓고 풀면 되는 부분..

public class Main {
    static int r, c, k, ans;
    static int[][] map;

    static int pre_r_size, cur_r_size;
    static int pre_c_size, cur_c_size;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pre_r_size = 3;
        pre_c_size = 3;
        cur_r_size = 3;
        cur_c_size = 3;

        map = new int[101][101];
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 0;
        while (map[r][c] != k) {
            if (cur_r_size >= cur_c_size) {
                pre_c_size = cur_c_size;
                cur_c_size = 0;
                for (int i = 1; i <= cur_r_size; i++) {
                    sortR(i); // 행정렬
                }

            } else {
                pre_r_size = cur_r_size;
                cur_r_size = 0;
                for (int i = 1; i <= cur_c_size; i++) {
                    sortC(i); // 열정렬
                }
            }

            ans++;

            if (ans > 100) {
                break;
            }

        }

        System.out.println(ans > 100 ? -1 : ans);

    }

    private static void sortC(int c) {
        count = new int[101];
        for (int i = 1; i <= pre_r_size; i++) {
            count[map[i][c]]++;
        }

        int size = 1;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (count[j] == i) {
                    map[size++][c] = j;
                    map[size++][c] = i;

                    if (size == 101) {
                        break;
                    }
                }
            }

        }

        for (int i = size; i <= 100; i++) {
            map[i][c] = 0;
        }


        if (cur_r_size < size) {
            cur_r_size = size;
        }
    }

    private static void sortR(int r) {
        count = new int[101];
        for (int i = 1; i <= pre_c_size; i++) {
            count[map[r][i]]++;
        }

//핵심코드
        int size = 1;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (count[j] == i) {
                    map[r][size++] = j;
                    map[r][size++] = i;

                    if (size == 101) {
                        break;
                    }
                }
            }
        }

        for (int i = size; i <= 100; i++) {
            map[r][i] = 0;
        }


        if (cur_c_size < size) {
            cur_c_size = size;
        }
    }
}