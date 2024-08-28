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
    static int map[][];
    static boolean visited[][];
    static boolean visited2[][];
    static boolean check;
    static Queue<Integer[]> num = new LinkedList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<map.length; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<map[0].length; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check = true;


        int count = 0;
        while (check == true){

            check = false;
            visited = new boolean[n][m];
            bfs(0,0); //공기를 2로 바꾼다

            visited2 = new boolean[n][m];

            for(int i=0; i<map.length; i++){
                for(int j=0; j<map[0].length; j++){
                    if(map[i][j] == 1 && visited2[i][j] != true){
                        fuze(i,j);
                    }
                }
            }

            for (int i=0; i<map.length; i++){
                for (int j=0; j<map[0].length; j++) {
                    if(map[i][j] == 1) check = true;
                }
            }

            count++;
        }

        System.out.print(count);
    }

    private static void fuze(int a, int b) {
        Queue<Integer[]> p = new LinkedList<>();
        p.add(new Integer[]{a,b});

        while (!p.isEmpty()){
            Integer[] poll = p.poll();
            int x = poll[0];
            int y = poll[1];

            if(visited2[x][y] == true) continue;
            visited2[x][y] = true;

            int count = 0;
            for(int i=0; i<4; i++){
                int nx = x+arr1[i];
                int ny = y+arr2[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(visited2[nx][ny] == true) continue;

                if(map[nx][ny] == 2){
                    count++;
                }
                if(map[nx][ny] == 1){
                    p.add(new Integer[]{nx,ny});
                }
            }

            if(count>=2){
                map[x][y]--;
                num.add(new Integer[]{x,y});
            }

        }

        while (!num.isEmpty()){
            Integer[] poll = num.poll();
            int x = poll[0];
            int y = poll[1];
            map[x][y] = 2;
        }

    }

    private static void bfs(int a, int b) {
        Queue<Integer[]> p = new LinkedList<>();
        p.add(new Integer[]{a,b});

        while (!p.isEmpty()){
            Integer[] poll = p.poll();
            int x = poll[0];
            int y = poll[1];

            if(visited[x][y] == true) continue;


            if(map[x][y] == 1) continue;


            visited[x][y] = true;
            if(map[x][y] != 2) map[x][y] = 2;

            for (int i=0; i<4; i++){
                int nx = x+arr1[i];
                int ny = y+arr2[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny] == 1) continue;

                p.add(new Integer[]{nx,ny});
            }

        }
    }
}
