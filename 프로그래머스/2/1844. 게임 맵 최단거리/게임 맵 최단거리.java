import java.util.PriorityQueue;
class Solution {
   int arr1[] = {0,1,0,-1};
    int arr2[] = {1,0,-1,0};
    boolean visited[][];
    int dist[][];
    int answer;
    class Node implements Comparable<Node>{
        int x;
        int y;
        int val;
        public Node(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    public int solution(int[][] maps) {
        answer = 1;

        visited = new boolean[maps.length][maps[0].length];
        dist = new int[maps.length][maps[0].length];


       bfs(0,0,maps);

       if(dist[maps.length-1][maps[0].length-1] == 0) return -1;
        else return dist[maps.length-1][maps[0].length-1];
    }
    public void bfs(int x, int y, int[][] maps) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(x,y,1));

        while (!q.isEmpty()){
            Node poll = q.poll();

            int nx = poll.x;
            int ny = poll.y;
            int val = poll.val;

            if(visited[nx][ny] == true) continue;
            visited[nx][ny] = true;
            dist[nx][ny] = val;

            for(int i=0; i<4; i++){
                int dx = nx+arr1[i];
                int dy = ny+arr2[i];
                if(dx >= maps.length || dy >= maps[0].length || dx < 0 || dy <0) continue;
                else{
                    if(maps[dx][dy] == 1 && visited[dx][dy] != true){
                        q.add(new Node(dx,dy,val+1));
                    }
                }
            }

        }
    }
}
