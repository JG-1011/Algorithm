import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
풀이
1. H는 총 행의 수를 나타냅니다.
2. 각 사람은 세로로 N칸 이상의 간격을 두고 앉아야 합니다. 즉, 한 사람이 차지하는 행의 수는 (N + 1)이 됩니다. 여기서 N은 간격이고 +1은 그 사람이 앉는 행입니다.
3. (H - 1)은 마지막 사람을 제외한 총 간격 수를 계산합니다. 마지막 사람도 앉아야 하기 때문에 마지막 사람을 위한 1을 더합니다.
4. (H - 1) / (N + 1)은 필요한 간격 수에 맞추어 총 몇 그룹으로 나눌 수 있는지를 계산합니다.
5. 마지막으로, +1을 해서 최소한 한 사람은 앉을 수 있도록 합니다.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int hight = (H - 1) / (N + 1) + 1;
        int width = (W - 1) / (M + 1) + 1;

        System.out.println(hight * width);

    }

}