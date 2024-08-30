import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 선언
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언

		int N = Integer.parseInt(br.readLine()); //가로세로 길이
		int[][] intArr = new int[N][N]; //입력한 배열
		int[][] mapArr = new int[N][N]; //누적 배열
		char num; //입력된 값 분할
		String str;
		for(int i = 0; i<N; i++) {		//한줄 단위
					str = br.readLine();
			
			for(int j = 0; j<N; j++) {	//한칸 단위
				num = (str.charAt(j));
				
				if(num=='.') {			//. 이면 0으로 세팅
					intArr[i][j] = 0;
				}else {
				intArr[i][j] = Character.getNumericValue(num); //int로 변환
				}
				
				//숫자 주위에 해당 숫자만큼 더해줌
				//배열 범위를 넘어가지 않게 조건부여
				if(i-1>=0) {
					mapArr[i-1][j] 		= mapArr[i-1][j] 	+ intArr[i][j];
					
					if(j-1>=0) {
					mapArr[i-1][j-1] 	= mapArr[i-1][j-1] 	+ intArr[i][j];
					}
					if(j+1<N) {
					mapArr[i-1][j+1] 	= mapArr[i-1][j+1]	+ intArr[i][j];	
					}
				}
				
				if(j+1<N) {
					mapArr[i][j+1] 		= mapArr[i][j+1] 	+ intArr[i][j];	
				}
				if(j-1>=0) {
					mapArr[i][j-1] 		= mapArr[i][j-1] 	+ intArr[i][j];	
				}
				
				if(i+1<N) {
					mapArr[i+1][j] 		= mapArr[i+1][j] 	+ intArr[i][j];
					if(j-1>=0) {
					mapArr[i+1][j-1] 	= mapArr[i+1][j-1] 	+ intArr[i][j];	
					}
					if(j+1<N) {
					mapArr[i+1][j+1] 	= mapArr[i+1][j+1] 	+ intArr[i][j];	
					}
				}		
			}
		}
		int mapNum; //누적값 세팅용
		int intNum; //* 표시용
		
		for(int i = 0; i<N; i++) {		//한줄씩 작성
			for(int j = 0; j<N; j++) {	//한칸씩 작성
				mapNum=mapArr[i][j];	//누적값 조회
				intNum=intArr[i][j];	//입력값 조회
				
				if(intNum>0) {			//입력값이 존재하면 "*" 로 변환
					bw.write("*");
				}else if(mapNum>=10) {	//누적값이 10 이상이면 "M"출력
					bw.write("M");
				}else {					//누적값 출력
					bw.write(mapNum+"");	
				}
			}
			bw.write("\n");				//개행
		}
		bw.close();
		br.close();
	}
}