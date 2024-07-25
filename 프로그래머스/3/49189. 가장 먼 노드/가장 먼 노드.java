import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] dist;
    static int max;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;

        visited = new boolean[n+1];
        dist = new int[n+1];
        list = new ArrayList[n+2];

        for(int i=1; i<n+2; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<edge.length; i++){
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }

        max = 0;
        moving(1);

        for(int i=1; i<dist.length; i++){
            if(dist[i] == max){
                answer++;
            }
        }
        return answer;
    }
    
    private static void moving(int a) {
        Queue q = new LinkedList();
        q.add(a);
        visited[1] = true;

        int count = 0;
        while (!q.isEmpty()){
            int node = (int) q.poll();

            count = dist[node] + 1;

            for(int i=0; i<list[node].size(); i++){
                int nextNode = list[node].get(i);

                if(visited[nextNode] == true){
                    continue;
                }

                visited[nextNode] = true;

                dist[nextNode] = count;

                if(dist[nextNode]>max){
                    max= dist[nextNode];
                }

                q.add(nextNode);
            }

        }
    }
}