import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arr[] = new int[9];
        int max = arr[0]; // 최댓값을 담을 변수
        int index = 0; // 최댓값의 위치를 담는 변수

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (max < arr[i]) {
                max = arr[i];
                index = i+1;
            }
        }
        System.out.println(max);
        System.out.println(index);
    }
}