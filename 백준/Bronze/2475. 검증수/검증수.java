import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0;  //모든 수의 제곰된 값을 더할 변수
        while (st.hasMoreTokens()) {
            int a = Integer.parseInt(st.nextToken());
            total += a*a;
        }
        System.out.print(total % 10);
    }
}