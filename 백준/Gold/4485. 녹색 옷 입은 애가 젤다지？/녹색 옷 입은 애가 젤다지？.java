import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    static int n;
    static int map[][];
    static int dist[][];

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int c;

        public Point(int x, int y, int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            return this.c - o.c;
        }
    }
   public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       int t = 1;
       while (true){
           n = Integer.parseInt(br.readLine());
           if(n == 0) break;

           map = new int[n][n]; //현재 지도
           dist = new int[n][n]; //최소값을 저장할 지도

           for(int i=0; i<n; i++){
               st = new StringTokenizer(br.readLine());
               for(int j=0; j<n; j++){
                   map[i][j] = Integer.parseInt(st.nextToken());
                   dist[i][j] = Integer.MAX_VALUE; //최소값을 저장할거니까 최대값으로 초기 셋팅
               }
           }

           cost();

           System.out.println("Problem "+ t + ": " + dist[n-1][n-1]);
           t++;
       }
   }

    private static void cost() {
        PriorityQueue<Point> q = new PriorityQueue<>(); //최소값을 갱신할거니까 우선순위 큐로 값저장
        q.add(new Point(0,0,map[0][0])); //시작지점
        dist[0][0] = map[0][0];

        while (!q.isEmpty()){
            Point poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int c = poll.c;

            if(c > dist[x][y]) continue; //우선순위큐라 똑같은 좌표가 다시 들어올수있음 값 비교해서 필요없는지 확인해줘야함

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue; //범위체크

                int nextCost = map[nx][ny] + c; //다음 위치를 도착했을때 값
                if(dist[nx][ny]>nextCost){ // 지금 저장값보다 계산값이 작으면
                    dist[nx][ny] = nextCost; // 값을 바꾼다 (최소값)
                    q.add(new Point(nx,ny,dist[nx][ny]));
                }
            }
        }
    }

}
