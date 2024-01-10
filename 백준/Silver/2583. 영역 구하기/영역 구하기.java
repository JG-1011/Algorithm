import java.util.*;
import java.io.*;

public class Main {
	
	static int M, N, K;
	static int[][] ddang;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int size;
	static ArrayList<Integer> result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //y축
		N = Integer.parseInt(st.nextToken()); //x축
		K = Integer.parseInt(st.nextToken());
		
		ddang = new int[M][N]; //행(y인 M) 열(y인 N)
		result = new ArrayList<>();
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int y=y1;y<y2;y++) {
				for(int x=x1;x<x2;x++) {
					ddang[y][x] = 1; //한칸씩 색칠해준다
				}
			}
		}
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(ddang[i][j] == 0) {
					size=1;
					dfs(i, j);
					result.add(size);
				}
			}
		}
		
		Collections.sort(result);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(result.size()+"\n");
		for(int r : result) {
			sb.append(r+" ");
		}
		bw.write(sb+"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int y, int x) {
		ddang[y][x] = 1; //visited를 따로 만들어주지 않고, 들어왔음을 1로 표시했다.
		
		for(int i=0;i<4;i++) {
			int nx = dx[i]+x;
			int ny = dy[i]+y;
			
			if(nx>=0 && ny >=0 && nx<N && ny<M && ddang[ny][nx]==0) {
				size++;
				dfs(ny,nx);
			}
		}
	}
}