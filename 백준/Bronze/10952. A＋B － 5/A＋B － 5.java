import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){					// true = 무한루프를 이용해
										// 특정 조건 만족시 무한루프 종료
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a == 0 && b == 0){		// 0 이 들어오면 멈춘다(break문)
                break;
            }
            System.out.println(a + b);
        }
    }
}