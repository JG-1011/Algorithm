import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toUpperCase();

        int[] alpa = new int[26];

        for (int i = 0; i < str.length(); i++) {
            int x = str.charAt(i) - 'A';
            alpa[x]++;
        }

        int max = 0;
        char result = ' ';
        boolean isMulti = false;

        for (int i = 0; i < alpa.length; i++) {
            if (alpa[i] > max) {
                max = alpa[i];
                result = (char) ('A' + i);
                isMulti = false;
            } else if (alpa[i] == max) {
                isMulti = true;
            }
        }
        System.out.println(isMulti ? "?" : result);
    }
}