import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
	
	public static int T;
	public static int N, M;
	public static int[] arr, brr;
	public static int answer = 0;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	T = Integer.parseInt(st.nextToken());
    	for(int t=0;t<T;t++) {
        	st = new StringTokenizer(br.readLine());
       	 	N = Integer.parseInt(st.nextToken());
       	 	M = Integer.parseInt(st.nextToken());
       	 	
       	 	arr = new int[N];
       	 	brr = new int[M];
       	 	
       	 	st = new StringTokenizer(br.readLine());
       	 	for(int i=0;i<N;i++) {
       	 		arr[i] = Integer.parseInt(st.nextToken());
       	 	}
       	 	
       	 	st = new StringTokenizer(br.readLine());
       	 	for(int i=0;i<M;i++) {
       	 		brr[i] = Integer.parseInt(st.nextToken());
       	 	}
       	 	Arrays.sort(brr);
       	 	
    		answer = 0;
       	 	for(int i=0;i<N;i++) {
       	 		binarySearch(arr[i]);
       	 	}
       	 	System.out.println(answer);
       	 	
    	}
 
 
    }
    
    public static void binarySearch(int target) {
    	int start = 0;
    	int end = M-1;
    	int middle = 0;
    	while(start <= end) {
    		middle = ( start + end ) / 2;
    		
    		if(target > brr[middle]) {
    			start = middle + 1;
    		}else {
    			end = middle - 1;
    		}
    		
    	}
    	
//    	System.out.println(start);
    	answer += start;
    }
    
}