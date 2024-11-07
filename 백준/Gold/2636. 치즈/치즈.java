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
    static int cheeze;
    static int map[][];
    static boolean visited[][];
    static boolean visited2[][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        int zero=0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    zero += 1;
                }
            }
        }

        if(zero == 0){
            System.out.println(0);
            System.out.println(0);

            return;
        }

        int count = 0;
        int val;
        boolean check = true;

        while (check){
            check = false;
            val = 0;
            visited = new boolean[n][m];
            //공기 채우기
            bfs();

            //공기를 다채웠으니 내부랑 분리됨
            //이제 치즈를 찾아서 녹인다
            visited2 = new boolean[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 1) chch(i,j); //치즈면 bfs돌린다
                }
            }

            count++;

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 1){
                        val += 1;
                        check = true;
                    }
                }
            }

            if(check != false){
                cheeze = val;
            }

        }

        System.out.println(count);

        if(count == 1){
            System.out.println(zero);
        } else{
            System.out.println(cheeze);
        }


    }

    private static void chch(int a, int b) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{a,b});

        while (!q.isEmpty()){
            Integer[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            if(visited2[x][y] == true) continue; //방문했으면 넘겨
            if(map[x][y] == 5 || map[x][y] == 0) continue; // 공기거나 밀폐된곳이면 넘겨
            visited2[x][y] = true;

            for(int i=0; i<4; i++){
                int nx = x + arr1[i];
                int ny = y + arr2[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                if(visited2[nx][ny] == true) continue;
                if(map[nx][ny] == 5){
                    map[x][y] = 0;
                    continue;
                }

                q.add(new Integer[]{nx,ny});

            }
        }

    }

    private static void bfs() {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{0,0});

        while (!q.isEmpty()){
            Integer[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            if(visited[x][y] == true) continue; //방문했으면 넘겨
            if(map[x][y] == 1) continue; // 치즈면 넘겨

            visited[x][y] = true;
            if(map[x][y] == 0) map[x][y] = 5; //공기로 변환

            for(int i=0; i<4; i++){
                int nx = x + arr1[i];
                int ny = y + arr2[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                if(visited[nx][ny] == true) continue;
                if(map[nx][ny] == 1) continue; //치즈거면 넘긴다

                q.add(new Integer[]{nx,ny});
            }

        }
    }

}
