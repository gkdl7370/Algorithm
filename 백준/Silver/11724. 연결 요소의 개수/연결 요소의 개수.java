import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int [][] arr;
    static boolean [] check;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int [n+1][n+1];
        check = new boolean[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = arr[b][a] = 1;
        }

        dfs(1);
        int count = 1;
        for(int i=1; i<check.length; i++){
            if(check[i] == false){
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    public static void dfs(int a){

        check[a] = true;

        for(int i=1; i<=n; i++){
            if(arr[a][i]==1 && check[i] == false){
                dfs(i);
            }
        }
    }
}
