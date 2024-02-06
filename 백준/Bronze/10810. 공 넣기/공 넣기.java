import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];

        int M = sc.nextInt();
        for(int i=0; i<M; i++) {
            int F = sc.nextInt(); // F번 바구니부터
            int E = sc.nextInt(); // E번 바구니까지
            int ball = sc.nextInt(); // ball번 공을 넣는다

            for(int j = F-1; j<E; j++)
            {
                arr[j] = ball;
            }
        }

        for (int baguni : arr)
            System.out.print(baguni + " ");
    }
}