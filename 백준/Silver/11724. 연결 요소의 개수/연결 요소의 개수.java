import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		static int N;
		static int[][] graph = new int[1001][1001];
		static boolean[] visit = new boolean[500000];
		//그룹에 정점이 들어왔는지 확인하기 위한 변수
		static int CC = 0;
		//연결이 없는 노드(정점)을 파악하기 위한 변수
		static int count = 0;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//출력값을 저장할 변수
		int result = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			DFS(i);
			//CC가 0이 아니라면 그룹에 들어온 노드가 있다는 뜻이니,
			//그룹의 개수(출력 값 변수)에 +1을 해준다.
			if(CC != 0) {
				result++;
			}
			//count가 정점의 개수와 같다면 연결이 없는 정점이기에
			//그룹의 개수에 +1을 해준다.
			if(count == N) {
				result++;
			}
			//확인이 끝났으면 CC와 count는 0으로 초기화
			CC = 0;
			count = 0;
		}
		System.out.println(result);
	}
	
	//탐색 메소드
	public static void DFS(int node) {
		visit[node] = true;
		
		for(int i = 1; i <= N; i++) {
			if(!visit[i] && graph[node][i] == 1) {
				DFS(i);
				CC++;
				count = 0;
			}
			//현재 정점과 연결된 정점이 없다면 count에 +1을 해준다.
			if(graph[node][i] == 0) {
				count++;
			}
		}
	}

}