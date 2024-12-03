import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int arr1[]={0,1,-1,0};
    static int arr2[]={1,0,0,-1};
    static int n;
    static int m;
    static char map[][];
    static int max = Integer.MIN_VALUE;
    static boolean visited[][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 'L'){
                    visited = new boolean[n][m];
                    bfs(i,j,0);
                }
            }
        }

        System.out.println(max);

    }

    private static void bfs(int q, int w, int o) {
        Queue<Integer[]> p = new LinkedList<>();
        p.add(new Integer[]{q,w,o});

        while (!p.isEmpty()){
            Integer poll[] = p.poll();
            int x = poll[0];
            int y = poll[1];
            int count = poll[2];

            if(x<0 || y<0 || x>=n || y>=m) continue;
            if(visited[x][y] == true) continue;
            visited[x][y] = true;

            max = Math.max(count,max);

            for(int i=0; i<4; i++){
                int nx = x + arr1[i];
                int ny = y + arr2[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(visited[nx][ny] == true) continue;

                if(map[nx][ny] == 'L') p.add(new Integer[]{nx,ny,count+1});
            }
        }
    }
}
