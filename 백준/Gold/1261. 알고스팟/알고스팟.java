import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] arr1 = {0, 1, -1, 0};
    static int[] arr2 = {1, 0, 0, -1};
    static int n, m;
    static int[][] map;
    static boolean visited[][];
    static int count = 0;
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int len;

        Point(int x, int y, int lne){
            this.x = x;
            this.y = y;
            this.len = lne;

        }

        @Override
        public int compareTo(Point o) {
            return this.len - o.len;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        bfs(0,0,0);

        System.out.println(count);
    }

    private static void bfs(int a, int b, int c) {
        PriorityQueue<Point> p = new PriorityQueue<>();
        p.add(new Point(a,b,c));

        while (!p.isEmpty()){
            Point poll = p.poll();

            int x = poll.x;
            int y = poll.y;
            int len = poll.len;

            if(x == m-1 && y == n-1){
                count = len;
                return;
            }

            if(visited[x][y] == true) continue;
            visited[x][y] = true;

            for(int i=0; i<4; i++){
                int nx = x + arr1[i];
                int ny = y + arr2[i];

                if(nx<0 || ny<0 || nx>=m || ny>=n) continue;
                if(visited[nx][ny] == true) continue;

                if(map[nx][ny] == 0){
                    p.add(new Point(nx,ny,len+0));
                } else {
                    p.add(new Point(nx,ny,len+1));
                }
            }
        }

    }
}
