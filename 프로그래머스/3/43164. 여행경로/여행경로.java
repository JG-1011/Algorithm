import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
        
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        
        List<String> path = new ArrayList<>();
        dfs("ICN", graph, path);
        
        return path.toArray(new String[0]);
    }
    
    public static void dfs(String airport, HashMap<String, PriorityQueue<String>> graph,  List<String> path) {
        while (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
            String next = graph.get(airport).poll();
            dfs(next, graph, path);
        }
        
        path.add(0, airport);
    }
}