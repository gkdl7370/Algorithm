import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int arr[] = {-1, 0, 0, 1}; //북 서 동 남
    static int arr2[]= {0, -1, 1, 0};
    static int map[][];
    static boolean visited[][];
    static int count;
    static int n;
    static int x, y, w;
    static int eat;
    static class Point implements Comparable<Point>{
        int nx;
        int ny;
        int len;

        public Point(int nx, int ny, int len){
            this.nx = nx;
            this.ny = ny;
            this.len = len;
        }

        @Override
        public int compareTo(Point o) {
            if (this.len != o.len) {
                return Integer.compare(this.len, o.len);
            }
            if (this.nx != o.nx) {
                return Integer.compare(this.nx, o.nx);
            }
            return Integer.compare(this.ny, o.ny);
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    map[i][j] = 0;
                    x = i;
                    y = j;
                }
            }
        }

        count = 0;
        eat = 0;
        w = 2;

        while (true){

            visited = new boolean[n][n];
            int[] val = bfs(x,y,0);
            if(val[2] == -1){
               break;
            }
            count += val[2];
            x=val[0];
            y=val[1];

            eat++;

            if(eat == w){
                w++;
                eat = 0;
            }
            
        }
        
        System.out.println(count);
    }

    private static int[] bfs(int a, int b, int c) {
        PriorityQueue<Point> p = new PriorityQueue<>();
        p.add(new Point(a,b,c));
        int weight = w;

        while (!p.isEmpty()){
            Point poll = p.poll();

            int nowX = poll.nx;
            int nowY = poll.ny;
            int len = poll.len;

            if(visited[nowX][nowY] == true) continue;
            visited[nowX][nowY] = true;

            if(map[nowX][nowY] != 0 && map[nowX][nowY] < weight){
                map[nowX][nowY] = 0;
                return new int[]{nowX,nowY,len};
            }

            for(int i=0; i<4; i++){
                int nextX = nowX + arr[i];
                int nextY = nowY + arr2[i];

                if(nextX<0 || nextY<0 || nextX>=n ||nextY>=n) continue;

                if(visited[nextX][nextY] == true) continue;

                if(map[nextX][nextY] > weight) continue;


                p.add(new Point(nextX,nextY,len+1));
            }

        }

        return new int[]{-1,-1,-1};
    }

}
