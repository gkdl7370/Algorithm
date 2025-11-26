import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n;
    static int count=0;
    static int map[][];
    static boolean vit[][];
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        vit = new boolean[n][n];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        search();

        System.out.println(count);
        Collections.sort(list);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    private static void search() {
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                if(map[i][j] == 1 && vit[i][j] == false){
                    bfs(i,j);
                    count ++;
                }
            }
        }
    }

    private static void bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a,b});
        vit[a][b] = true;
        int num = 1;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if(vit[nx][ny] == true) continue;
                if(map[nx][ny] == 0) continue;

                q.add(new int[]{nx,ny});
                vit[nx][ny] = true;
                num++;
            }
        }

        list.add(num);
    }


}

