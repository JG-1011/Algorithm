import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int score = sc.nextInt();
        int P = sc.nextInt();
 
        int[] ranklist = new int[N];
        int rank = 1; //등수
 
 
        for (int i = 0; i < N; i++) {
            ranklist[i] = sc.nextInt();
        }
 
 
        // 배열크기 N 과 랭킹최대크기 P가 같으면서 ,
        // 랭킹 리스트의 가장 작은 순위보다 새로운 스코어가 작다면 랭킹에 진입X
        if(N==P && score<=ranklist[ranklist.length-1])
            System.out.println(-1);
        else{
            for(int i=0; i<ranklist.length; i++){
                if(score < ranklist[i])
                    rank++; //  100(1등) 90(2)
                else
                    break; // 1등
            }
            System.out.println(rank);
        }
 
    }
}