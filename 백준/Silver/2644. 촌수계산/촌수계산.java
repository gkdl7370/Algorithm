import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int start,from;
    static int m;
    static int[][] arr;
    static boolean[] check;
    static int count=-1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        from = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        check = new boolean[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = arr[y][x] = 1;
        } //입력

        dfs(start,0);
        System.out.print(count);
    }

    public static void dfs(int a, int b){
        check[a] = true;

        if(a == from){
            count = b;
            return;
        }


        for(int i=1; i<=n; i++){
            if(check[i] == false && arr[a][i] == 1){
                dfs(i,b+1);
            }
        }
    }
}
