import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class bing{
    int x;
    int y;

    bing(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[][] arr;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<bing> q = new LinkedList<bing>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m]; // 빙산

        int yearCnt = 0; // 년

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean all = true; //다 녹았는지 체크

        while (all){
            int count=0;
            visited = new boolean[n][m];
            all= false;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(arr[i][j] > 0 && !visited[i][j]){ //바다가 아니고 방문한적이 없으면
                        bfs(i,j); //방문 체크를 위한 bfs
                        count++; //빙산이 이어져있으면 다시 들어 갈 일이 없다 2이상 부터는 대륙이 나눠진거
                        all = true; //빙산 있으니까 while 돌아야 함
                    }
                }
            }

            if(count>1){
                System.out.print(yearCnt);
                break;
            }

            int check[][] = new int[n][m];

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(arr[i][j]>0){
                        int bada=0;
                        for(int k=0;k<4;k++){
                            int nx = i+dx[k];
                            int ny = j+dy[k];
                            if(arr[nx][ny] == 0){
                                bada++;
                            }
                        }
                        int update = arr[i][j] - bada;
                        if(update<=0){
                            update = 0;
                        }
                        check[i][j] = update;
                    }
                }
            }
            yearCnt++;
            arr = check.clone();
        }

        if(!all){
            System.out.print(0);
        }

    }

    static void bfs(int a, int b) {
        q.add(new bing(a,b));

        visited[a][b]=true;

        while (!q.isEmpty()){
            bing xy = q.poll();
            int x=xy.x;
            int y=xy.y;
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx>=0 && ny>=0 && nx<n && ny<m){
                    if(arr[nx][ny]>0 && !visited[nx][ny]){
                        q.add(new bing(nx,ny));
                        visited[nx][ny] = true;
                    }
                }

            }
        }

    }

}