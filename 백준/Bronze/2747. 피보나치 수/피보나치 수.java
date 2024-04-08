import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        if (N==0){
            System.out.println(0);
        }
        else if(N == 1) {
            System.out.println(1);
        }
        else if (N > 1) {
            arr[0] = 0;
            arr[1] = 1;

            for (int i = 2; i <= N; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }

            System.out.println(arr[N]);
        }
    }
}
