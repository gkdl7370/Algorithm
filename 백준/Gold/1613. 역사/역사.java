import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int map[][] = new int[n+1][n+1];

//        for(int i=1; i<=n; i++){
//            for(int j=1; j<=n; j++) {
//                map[i][j] = Integer.MAX_VALUE;
//            }
//        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = -1;
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                    if(map[i][k] == -1 && map[k][j] == -1) map[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        for(int i=1; i<=q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(map[a][b] == 1) System.out.println(-1);
            else if(map[a][b] == -1) System.out.println(1);
            else System.out.println(0);
        }

    }
}
